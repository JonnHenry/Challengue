package com.example.challengue.Services;


import com.example.challengue.DTO.Response.CreateUserDTO;
import com.example.challengue.DTO.Response.UserAllDataDTO;
import com.example.challengue.DTO.Response.UserNotVaccinetDTO;
import com.example.challengue.DTO.UserRegisterDTO;
import com.example.challengue.Entities.Rol;
import com.example.challengue.Entities.User;

import java.util.Date;
import java.util.List;


public interface IUserService /*extends UserDetailsService*/ {

    public CreateUserDTO createUser(UserRegisterDTO userRegisterDTO);

    UserRegisterDTO updateCreateUser(UserRegisterDTO userRegisterDTO, String userId);

    public void deleteUserCreatedById(String userId);

    public UserAllDataDTO completeUserRegistration(String username, Date birthDate, String address, String telephone);

    public User findUserByUsername(String username);

    public List<UserAllDataDTO> getAllUsers();

    public UserAllDataDTO getUserById(String id);

    public List<Rol> getAllRolesByUsername(String username);

    public List<Rol> appendRolByUsernameAndIdRol(String username,Integer rolId);



}
