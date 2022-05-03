package com.example.challengue.Repositories;

import com.example.challengue.Entities.User;
import com.example.challengue.Entities.UserVaccine;
import com.example.challengue.Entities.UserVaccineKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryUserVaccine extends JpaRepository<UserVaccine, UserVaccineKey> {


    @Query("SELECT b FROM UserVaccine b")
    public List<UserVaccine> findDataByVaccinated();

    @Query("SELECT a FROM User a WHERE NOT EXISTS (SELECT b FROM UserVaccine b WHERE a.id = b.user.id)")
    public List<User> findByStatusNotVaccinated();

    public List<UserVaccine> findAllByVaccineVaccineName(String vaccineName);

    public List<UserVaccine> findAllByVaccinationDateBetween(Date publicationTimeStart, Date publicationTimeEnd);

    public void deleteByUserIdAndVaccineId(Integer idUser, Integer idVaccine);

    public Optional<UserVaccine> findByUserIdAndVaccineId(String userId, Integer vaccineId);

}
