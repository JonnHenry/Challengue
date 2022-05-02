package com.example.challengue.Services;

import com.developer.kruger.DTO.CreateUserDTO;
import com.developer.kruger.DTO.UserRegisterDTO;
import com.developer.kruger.Entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;


public interface IUserService extends UserDetailsService {

    public CreateUserDTO createUser(UserRegisterDTO userRegisterDTO);

    UserRegisterDTO updateCreateUser(UserRegisterDTO userRegisterDTO, String userId);

    public void deleteUserCreatedById(String userId);

    User completeUserRegistration(String username, Date birthDate, String address, String telephone);



}
