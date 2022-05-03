package com.example.challengue.Repositories;

import com.example.challengue.DTO.UserRegisterVaccineDTO;
import com.example.challengue.Entities.User;
import com.example.challengue.Entities.UserVaccine;
import com.example.challengue.Entities.UserVaccineKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryUserVaccine extends JpaRepository<UserVaccine, UserVaccineKey> {


    @Query("SELECT distinct a FROM User a WHERE EXISTS (SELECT b FROM UserVaccine b WHERE a.id = b.user.id)")
    public List<User> findDataByVaccinated();

    @Query("SELECT distinct a FROM User a WHERE NOT EXISTS (SELECT b FROM UserVaccine b WHERE a.id = b.user.id)")
    public List<User> findByStatusNotVaccinated();

    @Query("SELECT distinct a FROM User a WHERE EXISTS (SELECT b FROM UserVaccine b WHERE a.id = b.user.id and b.vaccine.vaccineName=:vaccineName)")
    public List<User> findAllByVaccineVaccineName(@Param("vaccineName") String vaccineName);

    @Query("SELECT distinct a FROM User a WHERE EXISTS (SELECT b FROM UserVaccine b WHERE a.id = b.user.id and b.vaccinationDate between :startDate and :endDate )")
    public List<User> findAllByVaccinationDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    public void deleteByUserIdAndVaccineId(Integer idUser, Integer idVaccine);

    public Optional<UserVaccine> findByUserUserNameAndVaccineVaccineName(String userId, Integer vaccineId);

}
