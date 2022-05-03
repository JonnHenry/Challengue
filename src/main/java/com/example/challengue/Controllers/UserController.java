package com.example.challengue.Controllers;


import com.example.challengue.DTO.CreateUserDTO;
import com.example.challengue.DTO.UserRegisterDTO;

import com.example.challengue.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        return new ResponseEntity<>(userService.createUser(userRegisterDTO), HttpStatus.CREATED);
    }




}
