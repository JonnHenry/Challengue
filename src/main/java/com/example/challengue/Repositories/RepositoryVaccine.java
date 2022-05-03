package com.example.challengue.Repositories;

import com.example.challengue.Entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryVaccine extends JpaRepository<Vaccine, Integer> {

    public Optional<Vaccine> findById(Integer vaccineId);

    public Optional<Vaccine> findByVaccineName(String vaccineName);

}
