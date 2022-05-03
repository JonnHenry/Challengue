package com.example.challengue.Services;

import com.example.challengue.DTO.Response.UserAllDataDTO;
import com.example.challengue.DTO.Response.UserNotVaccinetDTO;
import com.example.challengue.DTO.UserRegisterVaccineDTO;
import com.example.challengue.Entities.User;
import com.example.challengue.Entities.UserVaccine;
import com.example.challengue.Entities.Vaccine;

import java.util.Date;
import java.util.List;

public interface IUserVaccineService {

    public UserRegisterVaccineDTO createUserVaccine(User user, Vaccine vaccine, Date vaccinationDate, Integer numberDoses);

    public List<UserAllDataDTO> getAllUserVaccinated();

    public List<UserAllDataDTO> getAllUserNotVaccinated();

    public List<UserAllDataDTO> getAllUserByDateRange(Date startDate, Date endDate);

    public List<UserAllDataDTO> getUserVaccineByNameVaccine(String nameVaccine);

    public void deleteUserVaccineByUsernameAndIdVaccine(String idUser,Integer vaccineId);

    public UserVaccine getUserVaccineByUserNamedAndVaccineId(String username,Integer idVaccine);

}
