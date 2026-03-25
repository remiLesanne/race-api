package com.takima.race.registration.services;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceServices;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RunnerService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RunnerService runnerService;
    private final RaceServices raceServices;

    public RegistrationService(RegistrationRepository registrationRepository, RunnerService runnerService, RaceServices raceServices) {
        this.registrationRepository = registrationRepository;
        this.runnerService=runnerService;
        this.raceServices=raceServices;
    }

    public Registration createRegistration(Long raceId, Long runnerId){
        Race race = raceServices.getById(raceId);
        Runner runner = runnerService.getById(runnerId);
        
        Registration registration = new Registration();
        registration.setRace(race);
        registration.setRunner(runner);
        registration.setRegistrationDate(LocalDate.now());
        
        return registrationRepository.save(registration);
    }

    public List<Runner> getParticipants(Long raceId) {
        // Vérifier que la course existe
        raceServices.getById(raceId);
        
        // Récupérer toutes les inscriptions de cette course
        List<Registration> registrations = registrationRepository.findByRaceId(raceId);
        
        // Convertir les objets Runner directement
        return registrations.stream()
                .map(Registration::getRunner)
                .collect(Collectors.toList());
    }

    public List<Race> getRaces(Long runnerId) {
        // Vérifier que le coureur existe
        runnerService.getById(runnerId);
        
        // Récupérer toutes les inscriptions de ce coureur
        List<Registration> registrations = registrationRepository.findByRunnerId(runnerId);
        
        // Convertir les objets Race directement
        return registrations.stream()
                .map(Registration::getRace)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getParticipantsCount(Long raceId) {
        // Vérifier que la course existe
        raceServices.getById(raceId);
        
        // Compter les inscriptions pour cette course
        long count = registrationRepository.findByRaceId(raceId).size();
        
        // Retourner le résultat dans un Map avec la clé "count"
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);
        return response;
    }
}
