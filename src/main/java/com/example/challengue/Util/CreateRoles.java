package com.example.challengue.Util;


import com.example.challengue.DTO.Response.CreateUserDTO;
import com.example.challengue.DTO.RolDTO;
import com.example.challengue.DTO.UserRegisterDTO;
import com.example.challengue.Entities.Rol;
import com.example.challengue.Entities.User;
import com.example.challengue.Exception.ResourceNotFoundException;
import com.example.challengue.Repositories.RepositoryRol;
import com.example.challengue.Repositories.RepositoryUser;
import com.example.challengue.Services.IRolService;
import com.example.challengue.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

/*
//To create roles first Time


@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    private IRolService rolService;

    @Autowired
    private RepositoryRol rolRepository;

    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        RolDTO rolDTOADMIN = new RolDTO();
        rolDTOADMIN.setRol("ADMIN");
        rolService.createRol(rolDTOADMIN);

        RolDTO rolDTOUSER = new RolDTO();
        rolDTOUSER.setRol("USER");
        rolService.createRol(rolDTOUSER);

        User user = new User();
        user.setId("0105476097");
        user.setNames("0105476097".toUpperCase());
        user.setSurnames("Campoberde Avila".toUpperCase());
        user.setEmail("jonnathancampoberde@gmail.com");
        user.setUserName(generateUserName("jonnathancampoberde@gmail.com"));
        user.setPassword(passwordEncoder.encode("0105476097"));

        Rol roles = rolRepository.findByRol("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "rol", "USER"));
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
    }

    private String generateUserName(String email){
        String userName = email.trim().toLowerCase().substring(0,email.indexOf("@"));
        return userName;
    }


}

*/