package com.bsuir.vas.controller;

import com.bsuir.vas.model.ParticipantModelForView;
import com.bsuir.vas.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping("/participants")
    public List<ParticipantModelForView> getParticipants() {

        return participantService.getParticipants();
    }
}
