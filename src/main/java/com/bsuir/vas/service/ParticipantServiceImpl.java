package com.bsuir.vas.service;

import com.bsuir.vas.converter.ParticipantConverter;
import com.bsuir.vas.entity.ParticipantEntity;
import com.bsuir.vas.entity.PaymentEntity;
import com.bsuir.vas.exception.NotFoundException;
import com.bsuir.vas.model.ParticipantModelForCreating;
import com.bsuir.vas.model.ParticipantModelForView;
import com.bsuir.vas.repository.ParticipantRepository;
import com.bsuir.vas.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public List<ParticipantModelForView> getParticipants(String firstName, String lastName, String middleName) {

        List<ParticipantModelForView> participantModelsForView =
                ParticipantConverter.toParticipantModels(participantRepository
                        .findAllByParameters(firstName, lastName, middleName));

        participantModelsForView.forEach(participantModelForView -> {
            List<Long> uniquePayments = new ArrayList<>(paymentRepository
                                                .findAllByParticipantId(participantModelForView.getId())
                                                .stream()
                                                .mapToLong(PaymentEntity::getYear)
                                                .boxed()
                                                .collect(Collectors.toSet())
                                                .stream()
                                                .sorted()
                                                .collect(Collectors.toList()));

            participantModelForView.setPaymentYears(uniquePayments);
        });

        return participantModelsForView;
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public ParticipantModelForView getParticipant(long participantId) {

        ParticipantEntity participantEntity = participantRepository.findOneById(participantId);

        if(participantEntity != null) {
            ParticipantModelForView participantModelForView =
                    ParticipantConverter.toParticipantModel(participantEntity);

            participantModelForView.setPaymentYears(paymentRepository.findAllByParticipantId(participantId)
                    .stream()
                    .mapToLong(PaymentEntity::getYear)
                    .boxed()
                    .collect(Collectors.toList()));

            return participantModelForView;
        }

        throw new NotFoundException("Participant is not found!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public ParticipantModelForCreating createParticipant(ParticipantModelForCreating participantModelForCreating) {

        participantRepository.save(ParticipantConverter.toParticipantEntity(participantModelForCreating));
        return participantModelForCreating;
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public ParticipantModelForCreating updateParticipant(long participantId, ParticipantModelForCreating participantModelForCreating) {

        ParticipantEntity participantEntity = participantRepository.findOneById(participantId);

        if(participantEntity != null) {
            participantEntity.setFirstName(participantModelForCreating.getFirstName());
            participantEntity.setLastName(participantModelForCreating.getLastName());
            participantEntity.setMiddleName(participantModelForCreating.getMiddleName());

            participantRepository.save(participantEntity);

            return participantModelForCreating;
        }

        throw new NotFoundException("Participant is not found!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public void deleteParticipant(long participantId) {

        ParticipantEntity participantEntity = participantRepository.findOneById(participantId);

        if(participantEntity != null) {
            participantRepository.deleteOneById(participantId);
            paymentRepository.deleteAllByParticipantId(participantId);
        } else {
            throw new NotFoundException("Participant is not found!");
        }
    }
}
