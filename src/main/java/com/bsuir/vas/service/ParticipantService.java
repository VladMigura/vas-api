package com.bsuir.vas.service;

import com.bsuir.vas.model.ParticipantModelForCreating;
import com.bsuir.vas.model.ParticipantModelForView;

import java.util.List;

public interface ParticipantService {

    List<ParticipantModelForView> getParticipants(String firstName, String lastName, String middleName);
    ParticipantModelForView getParticipant(long participantId);
    ParticipantModelForCreating createParticipant(ParticipantModelForCreating participantModelForCreating);
    ParticipantModelForCreating updateParticipant(long participantId,
                                              ParticipantModelForCreating participantModelForCreating);
    void deleteParticipant(long participantId);
}
