package com.example.challengue.Controllers;


import com.example.challengue.DTO.CreateUserDTO;
import com.example.challengue.DTO.RolDTO;
import com.example.challengue.DTO.UserRegisterDTO;
import com.example.challengue.Services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/rol/")
public class RolController {

    @Autowired
    private IRolService rolService;


    @PostMapping("/create")
    public ResponseEntity<RolDTO> createUser(@Valid @RequestBody RolDTO rolDTO){
        return new ResponseEntity<>(rolService.createRol(rolDTO), HttpStatus.CREATED);
    }



}
