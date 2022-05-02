package com.example.challengue.Services;


import com.developer.kruger.DTO.*;
import com.developer.kruger.DTO.UserNotVaccinetDTO;
import com.developer.kruger.DTO.UsersVaccineDTO;
import com.developer.kruger.Entities.User;
import com.developer.kruger.Entities.UserVaccine;
import com.developer.kruger.Entities.Vaccine;
import com.developer.kruger.Exception.ResourceNotFoundException;
import com.developer.kruger.Repositories.RepositoryUserVaccine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserVaccineServiceImpl implements IUserVaccineService {

    @Autowired
    private RepositoryUserVaccine repositoryUserVaccine;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UsersVaccineDTO createUserVaccine(User user, Vaccine vaccine, Date vaccinationDate, Integer numberDoses) {
        UserVaccine userVaccine = new UserVaccine();
        userVaccine.setVaccine(vaccine);
        userVaccine.setUser(user);
        userVaccine.setVaccinationDate(vaccinationDate);
        userVaccine.setNumberDoses(numberDoses);
        return mapUserVaccineToUsersVaccineDTO(repositoryUserVaccine.save(userVaccine));
    }

    @Override
    public List<UsersVaccineDTO> allUserVaccinated() {
        return repositoryUserVaccine.findByStatusVaccinated()
                .stream()
                .map(user -> mapUserVaccineToUsersVaccineDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserNotVaccinetDTO> allUserNotVaccinated() {
        return repositoryUserVaccine.findByStatusNotVaccinated()
                .stream()
                .map(user->mapUserToUserNotVaccinetDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsersVaccineDTO> allUserByDateRange(Date startDate, Date endDate) {
        return repositoryUserVaccine.findAllByVaccinationDateBetween(startDate,endDate)
                .stream()
                .map(userVaccine -> mapUserVaccineToUsersVaccineDTO(new UserVaccine()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UsersVaccineDTO> searchUserVaccineByNameVaccine(String nameVaccine) {
        return repositoryUserVaccine.findAllByVaccineVaccineName(nameVaccine)
                .stream()
                .map(userVaccine -> mapUserVaccineToUsersVaccineDTO(userVaccine))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserVaccineById(String idUser, Integer idVaccine) {
        UserVaccine userVaccine = repositoryUserVaccine.findByUserIdAndVaccineVaccineId(idUser,idVaccine)
                .orElseThrow(() -> new ResourceNotFoundException("User", "idUser idVaccine", idUser+" "+idVaccine.toString()));

        repositoryUserVaccine.delete(userVaccine);
    }

    private UsersVaccineDTO mapUserVaccineToUsersVaccineDTO (UserVaccine userVaccine){
        return modelMapper.map(userVaccine, UsersVaccineDTO.class);
    }


    /**
     * This method map a object and returns the CreateUserDTO (Data Transfer Object)
     * @param user User The object to be maped
     * @return UserNotVaccinetDTO
     */
    private UserNotVaccinetDTO mapUserToUserNotVaccinetDTO(User user) {
        return  modelMapper.map(user, UserNotVaccinetDTO.class);
    }
}
