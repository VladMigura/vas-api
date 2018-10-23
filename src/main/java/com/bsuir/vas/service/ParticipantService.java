package com.bsuir.vas.service;

import com.bsuir.vas.model.ParticipantModelForView;

import java.util.List;

public interface ParticipantService {

    List<ParticipantModelForView> getParticipants();
    ParticipantModelForView getParticipant();
    ParticipantModelForView updateParticipant();
    void deleteParticipant();
}
