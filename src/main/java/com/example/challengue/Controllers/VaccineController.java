package com.example.challengue.Controllers;

import com.example.challengue.DTO.CreateUserDTO;
import com.example.challengue.DTO.UserRegisterDTO;
import com.example.challengue.DTO.VaccineDTO;
import com.example.challengue.Services.IUserService;
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
@RequestMapping("/api/vaccine/")
public class VaccineController {


    @Autowired
    private IVaccineService vaccineService;

    @PostMapping("/create")
    public ResponseEntity<VaccineDTO> createVaccine(@Valid @RequestBody VaccineDTO vaccineDTO){
        return new ResponseEntity<>(vaccineService.createVaccine(vaccineDTO), HttpStatus.CREATED);
    }
}
