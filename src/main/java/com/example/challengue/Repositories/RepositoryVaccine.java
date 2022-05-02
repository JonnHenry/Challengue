package com.example.challengue.Repositories;

import com.developer.kruger.Entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryVaccine extends JpaRepository<Vaccine, Integer> {

    public Optional<Vaccine> findByVaccineId(String vaccineId);

    public Optional<Vaccine> findByNameVaccine(String vaccineId);
}
