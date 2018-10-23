package com.bsuir.vas.service;

import com.bsuir.vas.entity.ParticipantEntity;
import com.bsuir.vas.entity.PaymentEntity;
import com.bsuir.vas.exception.NotFoundException;
import com.bsuir.vas.model.PaymentModel;
import com.bsuir.vas.repository.ParticipantRepository;
import com.bsuir.vas.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public PaymentModel createPayments(PaymentModel paymentModel) {

        ParticipantEntity participantEntity = participantRepository.findOneById(paymentModel.getParticipantId());

        if(participantEntity != null) {
            paymentModel.getYears().forEach(year -> {
                PaymentEntity paymentEntity = PaymentEntity.builder()
                        .participantEntity(participantEntity)
                        .year(year)
                        .build();

                paymentRepository.save(paymentEntity);
            });

            return paymentModel;
        }

        throw new NotFoundException("Participant is not found!");
    }

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public void deletePayments(PaymentModel paymentModel) {

        paymentRepository.deleteAllByParameters(paymentModel.getParticipantId(), paymentModel.getYears());
    }
}
