package com.bsuir.vas.service;

import com.bsuir.vas.model.ParticipantModelForView;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticipantServiceImpl implements ParticipantService {

    @Override
    @PreAuthorize("@securityUtility.isAuthenticated()")
    public List<ParticipantModelForView> getParticipants() {

        return null;
    }

    @Override
    public ParticipantModelForView getParticipant() {
        return null;
    }

    @Override
    public ParticipantModelForView updateParticipant() {
        return null;
    }

    @Override
    public void deleteParticipant() {

    }
}
