package com.example.challengue.Controllers;


import com.example.challengue.DTO.CreateUserDTO;
import com.example.challengue.DTO.UserCompleteRegisterDTO;
import com.example.challengue.DTO.UserRegisterDTO;
import com.example.challengue.DTO.UsersVaccineDTO;
import com.example.challengue.Entities.User;
import com.example.challengue.Entities.Vaccine;
import com.example.challengue.Services.IUserService;
import com.example.challengue.Services.IUserVaccineService;
import com.example.challengue.Services.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user_vaccine/")
public class UserVaccineController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IVaccineService vaccineService;
    @Autowired
    private IUserVaccineService iUserVaccineService;

    @PostMapping("/create")
    public ResponseEntity<UsersVaccineDTO> createUserVaccine(@Valid @RequestBody UserCompleteRegisterDTO userCompleteRegisterDTO){
        User user = userService.findUserByUsername(userCompleteRegisterDTO.getUsername());
        Vaccine vaccine = vaccineService.getVaccineById(userCompleteRegisterDTO.getIdVaccine());
        return new ResponseEntity<>(iUserVaccineService.createUserVaccine(user,vaccine,userCompleteRegisterDTO.getDateVaccination(),userCompleteRegisterDTO.getNumberDoses()), HttpStatus.CREATED);
    }
}
