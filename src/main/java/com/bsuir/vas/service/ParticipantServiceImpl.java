package com.bsuir.vas.service;

import com.bsuir.vas.converter.ParticipantConverter;
import com.bsuir.vas.entity.ParticipantEntity;
import com.bsuir.vas.entity.PaymentEntity;
import com.bsuir.vas.model.ParticipantModelForCreating;
import com.bsuir.vas.model.ParticipantModelForView;
import com.bsuir.vas.repository.ParticipantRepository;
import com.bsuir.vas.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
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
            participantModelForView.setPaymentYears(paymentRepository
                    .findAllByParticipantId(participantModelForView.getId())
                    .stream()
                    .mapToLong(PaymentEntity::getYear)
                    .boxed()
                    .collect(Collectors.toList()));
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


        }

        return null;
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public ParticipantModelForCreating createParticipant(ParticipantModelForCreating participantModelForCreating) {
        return null;
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public ParticipantModelForCreating updateParticipant(long participantId, ParticipantModelForCreating participantModelForCreating) {
        return null;
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public void deleteParticipant(long participantId) {

    }
}
