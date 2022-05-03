package com.example.challengue.Services;


import com.example.challengue.DTO.CreateUserDTO;
import com.example.challengue.DTO.UserRegisterDTO;
import com.example.challengue.Entities.User;

import java.util.Date;


public interface IUserService /*extends UserDetailsService*/ {

    public CreateUserDTO createUser(UserRegisterDTO userRegisterDTO);

    UserRegisterDTO updateCreateUser(UserRegisterDTO userRegisterDTO, String userId);

    public void deleteUserCreatedById(String userId);

    public User completeUserRegistration(String username, Date birthDate, String address, String telephone);

    public User findUserByUsername(String username);



}
