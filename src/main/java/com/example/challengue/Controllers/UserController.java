package com.example.challengue.Controllers;


import com.example.challengue.DTO.CreateUserDTO;
import com.example.challengue.DTO.UserRegisterDTO;

import com.example.challengue.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/user/create")
    public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        return new ResponseEntity<>(iUserService.createUser(userRegisterDTO), HttpStatus.CREATED);
    }



}
