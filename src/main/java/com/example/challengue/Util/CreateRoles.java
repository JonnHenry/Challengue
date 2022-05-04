package com.example.challengue.Util;


import com.example.challengue.Entities.Rol;
import com.example.challengue.Entities.User;
import com.example.challengue.Repositories.RepositoryRol;
import com.example.challengue.Repositories.RepositoryUser;
import com.example.challengue.Services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

/*
To create roles first Time
 */

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RepositoryRol rolRepository;

    @Autowired
    RepositoryUser userRepository;
    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol("ADMIN");
        Rol rolUser = new Rol("USER");
        rolRepository.save(rolAdmin);
        rolRepository.save(rolUser);
        User userAdmin = new User();
        userAdmin.setId("0105476097");
        userAdmin.setNames("Jonnathan Campoberde");
        userAdmin.setSurnames("Campoberde Avila");
        userAdmin.setEmail("jonnathancampoberde@gmail.com");
        userAdmin.setTelephone("0984655221");
        userAdmin.setBirthDate(new Date("21/01/1996"));
        userAdmin.setAddress("No definido");
        userAdmin.setPassword("0105476097");
        userAdmin.setRoles(Collections.singleton(rolAdmin));
        userRepository.save(userAdmin);

    }
}
