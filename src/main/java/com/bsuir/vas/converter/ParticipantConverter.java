package com.bsuir.vas.converter;

import com.bsuir.vas.entity.ParticipantEntity;
import com.bsuir.vas.model.ParticipantModelForCreating;
import com.bsuir.vas.model.ParticipantModelForView;

import java.util.ArrayList;
import java.util.List;

public class ParticipantConverter {

    public static ParticipantModelForView toParticipantModel(ParticipantEntity participantEntity) {

        return ParticipantModelForView.builder()
                .id(participantEntity.getId())
                .firstName(participantEntity.getFirstName())
                .lastName(participantEntity.getLastName())
                .middleName(participantEntity.getMiddleName())
                .build();
    }

    public static List<ParticipantModelForView> toParticipantModels(List<ParticipantEntity> participantEntities) {

        List<ParticipantModelForView> participantModelForViews = new ArrayList<>();

        participantEntities.forEach(participantEntity -> {
            participantModelForViews.add(ParticipantConverter.toParticipantModel(participantEntity));
        });

        return participantModelForViews;
    }

    public static ParticipantEntity toParticipantEntity(ParticipantModelForCreating participantModelForCreating) {

        return ParticipantEntity.builder()
                .firstName(participantModelForCreating.getFirstName())
                .lastName(participantModelForCreating.getLastName())
                .middleName(participantModelForCreating.getMiddleName())
                .build();
    }
}
