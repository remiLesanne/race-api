package com.takima.race.race.controllers;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RaceController {

    private final RaceServices raceServices;

    public RaceController(RaceServices raceServices) {
        this.raceServices = raceServices;
    }

    @GetMapping
    public List<Race> getAll(@RequestParam(required = false) String location) {
        // exemple d'url avec postman : {{baseUrl}}/races?location=Paris
        return raceServices.getAll(location);
    }

    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceServices.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Race create(@RequestBody Race race) {
        return raceServices.create(race);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Race update(@PathVariable Long id, @RequestBody Race race) {
        return raceServices.update(id, race);
    }
}
