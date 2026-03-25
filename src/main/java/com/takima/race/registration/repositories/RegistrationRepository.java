package com.takima.race.registration.repositories;

import com.takima.race.registration.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByRaceId(Long raceId);
    List<Registration> findByRunnerId(Long runnerId);
    Optional<Registration> findByRaceIdAndRunnerId(Long raceId, Long runnerId);
}
