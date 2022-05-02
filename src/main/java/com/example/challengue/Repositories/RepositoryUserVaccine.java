package com.example.challengue.Repositories;

import com.developer.kruger.Entities.User;
import com.developer.kruger.Entities.UserVaccine;
import com.developer.kruger.Entities.UserVaccineKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryUserVaccine extends JpaRepository<UserVaccine, UserVaccineKey> {

    public List<UserVaccine> findByVaccine(String vaccine);

    @Query("SELECT b FROM UserVaccine b")
    public List<UserVaccine> findByStatusVaccinated();

    @Query("SELECT a FROM User a WHERE NOT EXISTS (SELECT b FROM UserVaccine b WHERE a.id = b.id.userId)")
    public List<User> findByStatusNotVaccinated();

    public List<UserVaccine> findAllByVaccineVaccineName(String vaccineName);

    public List<UserVaccine> findAllByVaccinationDateBetween(Date publicationTimeStart, Date publicationTimeEnd);

    //eleteUserVaccineById(Integer idUser, Integer idVaccine)

    public Optional<UserVaccine> findByUserIdAndVaccineVaccineId(String userId, Integer vaccineId);

}
