package com.bsuir.vas.controller;

import com.bsuir.vas.model.ParticipantModelForCreating;
import com.bsuir.vas.model.ParticipantModelForView;
import com.bsuir.vas.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/participants")
    public List<ParticipantModelForView> getParticipants(@RequestParam(name = "firstName", required = false) String firstName,
                                                         @RequestParam(name = "lastName", required = false) String lastName,
                                                         @RequestParam(name = "middleName", required = false) String middleName) {

        return participantService.getParticipants(firstName, lastName, middleName);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/participant/{participantId}")
    public ParticipantModelForView getParticipant(@PathVariable long participantId) {

        return participantService.getParticipant(participantId);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/participant")
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipantModelForCreating createParticipant(@RequestBody ParticipantModelForCreating participantModelForCreating) {

        return participantService.createParticipant(participantModelForCreating);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/participant/{participantId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipantModelForCreating updateParticipant(@PathVariable long participantId,
                                                         @RequestBody ParticipantModelForCreating participantModelForCreating) {

        return participantService.updateParticipant(participantId, participantModelForCreating);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/participant/{participantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParticipant(@PathVariable long participantId) {

        participantService.deleteParticipant(participantId);
    }
}
