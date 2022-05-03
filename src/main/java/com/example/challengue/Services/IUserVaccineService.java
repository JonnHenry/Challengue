package com.example.challengue.Services;

import com.example.challengue.DTO.UserNotVaccinetDTO;
import com.example.challengue.DTO.UsersVaccineDTO;
import com.example.challengue.Entities.User;
import com.example.challengue.Entities.Vaccine;

import java.util.Date;
import java.util.List;

public interface IUserVaccineService {

    public UsersVaccineDTO createUserVaccine(User user, Vaccine vaccine, Date vaccinationDate, Integer numberDoses);

    public List<UsersVaccineDTO> allUserVaccinated();

    public List<UserNotVaccinetDTO> allUserNotVaccinated();

    public List<UsersVaccineDTO> allUserByDateRange(Date startDate, Date endDate);

    public List<UsersVaccineDTO> searchUserVaccineByNameVaccine(String nameVaccine);

    public void deleteUserVaccineById(String idUser,Integer idVaccine);

}
