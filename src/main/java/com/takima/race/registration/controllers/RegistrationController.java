package com.takima.race.registration.controllers;

import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.services.RegistrationService;
import com.takima.race.runner.entities.Runner;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/races")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/{raceId}/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public Registration registration(@PathVariable Long raceId, @RequestBody java.util.Map<String, Long> body) {
        return registrationService.createRegistration(raceId, body.get("runnerId"));
    }

    @GetMapping("/{raceId}/registrations")
    public List<Runner> getParticipants(@PathVariable Long raceId) {
        return registrationService.getParticipants(raceId);
    }

    @GetMapping("/{raceId}/participants/count")
    public Map<String, Long> getParticipantsCount(@PathVariable Long raceId) {
        return registrationService.getParticipantsCount(raceId);
    }

}