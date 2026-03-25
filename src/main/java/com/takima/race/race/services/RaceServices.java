package com.takima.race.race.services;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.runner.entities.Runner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class RaceServices {
    private final RaceRepository raceRepository;

    public RaceServices(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getAll() {
        return raceRepository.findAll();
    }

    public Race getById(Long id) {
        return raceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race %s not found", id)
                ));
    }

    public Race create(Race race){
        return raceRepository.save(race);
    }

    public Race update(Long id, Race race) {
        Race course = getById(id);

        course.setDate(race.getDate());
        course.setName(race.getName());
        course.setLocation(race.getLocation());
        course.setMaxParticipants(race.getMaxParticipants());

        return raceRepository.save(course);
    }

}
