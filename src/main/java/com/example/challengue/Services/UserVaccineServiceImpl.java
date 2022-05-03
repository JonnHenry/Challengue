package com.example.challengue.Services;


import com.example.challengue.DTO.Response.UserAllDataDTO;
import com.example.challengue.DTO.UserRegisterVaccineDTO;
import com.example.challengue.Entities.User;
import com.example.challengue.Entities.UserVaccine;
import com.example.challengue.Entities.Vaccine;
import com.example.challengue.Exception.ResourceNotFoundException;
import com.example.challengue.Repositories.RepositoryUserVaccine;
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
    public UserRegisterVaccineDTO createUserVaccine(User user, Vaccine vaccine, Date vaccinationDate, Integer numberDoses) {
        UserVaccine userVaccine = new UserVaccine();
        userVaccine.setVaccine(vaccine);
        userVaccine.setUser(user);
        userVaccine.setVaccinationDate(vaccinationDate);
        userVaccine.setNumberDoses(numberDoses);
        return mapUserVaccineToUserRegisterVaccineDTO(repositoryUserVaccine.save(userVaccine));
    }

    @Override
    public List<UserAllDataDTO> getAllUserVaccinated() {
        return repositoryUserVaccine.findDataByVaccinated()
                .stream()
                .map(user -> mapUserToUserAllDataDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserAllDataDTO> getAllUserNotVaccinated() {
        return repositoryUserVaccine.findByStatusNotVaccinated()
                .stream()
                .map(user->mapUserToUserAllDataDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserAllDataDTO> getAllUserByDateRange(Date startDate, Date endDate) {
        return repositoryUserVaccine.findAllByVaccinationDateBetween(startDate,endDate)
                .stream()
                .map(userVaccine -> mapUserToUserAllDataDTO(userVaccine))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserAllDataDTO> getUserVaccineByNameVaccine(String nameVaccine) {
        return repositoryUserVaccine.findAllByVaccineVaccineName(nameVaccine)
                .stream()
                .map(userVaccine -> mapUserToUserAllDataDTO(userVaccine))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserVaccineByUsernameAndIdVaccine(String username, Integer vaccineId) {
        UserVaccine userVaccine = repositoryUserVaccine.findByUserUserNameAndVaccineVaccineName(username,vaccineId)
                .orElseThrow(() -> new ResourceNotFoundException("UserVaccine", "username idVaccine", username+" "+vaccineId.toString()));
        repositoryUserVaccine.delete(userVaccine);
    }

    @Override
    public UserVaccine getUserVaccineByUserNamedAndVaccineId(String username,Integer idVaccine) {
        return repositoryUserVaccine.findByUserUserNameAndVaccineVaccineName(username,idVaccine)
                .orElseThrow(() -> new ResourceNotFoundException("UserVaccine", "username idVaccine", username+" "+idVaccine.toString()));
    }

    private UserRegisterVaccineDTO mapUserVaccineToUserRegisterVaccineDTO (UserVaccine userVaccine){
        return modelMapper.map(userVaccine, UserRegisterVaccineDTO.class);
    }

    private UserAllDataDTO mapUserToUserAllDataDTO (User userData){
        return modelMapper.map(userData, UserAllDataDTO.class);
    }

}
