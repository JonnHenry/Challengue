package com.example.challengue.Controllers;


import com.example.challengue.DTO.Response.UserAllDataDTO;
import com.example.challengue.DTO.Response.UserNotVaccinetDTO;
import com.example.challengue.DTO.UserRegisterVaccineDTO;
import com.example.challengue.Entities.User;
import com.example.challengue.Entities.Vaccine;
import com.example.challengue.Services.IUserService;
import com.example.challengue.Services.IUserVaccineService;
import com.example.challengue.Services.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user_vaccine")
public class UserVaccineController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IVaccineService vaccineService;
    @Autowired
    private IUserVaccineService userVaccineService;

    @PostMapping
    public ResponseEntity<UserRegisterVaccineDTO> createUserVaccine(@Valid @RequestBody UserRegisterVaccineDTO userRegisterVaccineDTO){
        User user = userService.findUserByUsername(userRegisterVaccineDTO.getUsername());
        Vaccine vaccine = vaccineService.getVaccineById(userRegisterVaccineDTO.getIdVaccine());
        return new ResponseEntity<>(userVaccineService.createUserVaccine(user,vaccine,userRegisterVaccineDTO.getDateVaccination(),userRegisterVaccineDTO.getNumberDoses()), HttpStatus.CREATED);
    }

    @PutMapping("/{username}/{vaccine_id}")
    public ResponseEntity<UserRegisterVaccineDTO> updateUserVaccine(@Valid @PathVariable(name = "username") String username,@Valid @PathVariable(name = "vaccine_id") Integer vaccineId, @Valid @RequestBody UserRegisterVaccineDTO userRegisterVaccineDTO){
        User user = userService.findUserByUsername(userRegisterVaccineDTO.getUsername());
        Vaccine vaccine = vaccineService.getVaccineById(userRegisterVaccineDTO.getIdVaccine());
        return new ResponseEntity<>(userVaccineService.createUserVaccine(user,vaccine,userRegisterVaccineDTO.getDateVaccination(),userRegisterVaccineDTO.getNumberDoses()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{username}/{vaccine_id}")
    public ResponseEntity<String> deleteUserVaccine(@Valid @PathVariable(name = "username") String username,@Valid @PathVariable(name = "vaccine_id") Integer vaccineId){
        userVaccineService.deleteUserVaccineByUsernameAndIdVaccine(username,vaccineId);
        return new ResponseEntity<>("Registro eliminado con exito", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/status")
    public ResponseEntity<List<UserAllDataDTO>> getVaccinated(@Valid @RequestParam("is_vaccinated") Boolean isVaccinated){
        if (isVaccinated){
            return new ResponseEntity<>(userVaccineService.getAllUserVaccinated(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(userVaccineService.getAllUserNotVaccinated(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/vaccine")
    public ResponseEntity<List<UserAllDataDTO>> getNameVaccinated(@Valid @RequestParam("name_vaccine") String nameVaccine){
        return new ResponseEntity<>(userVaccineService.getUserVaccineByNameVaccine(nameVaccine), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/find/between")
    public ResponseEntity<List<UserAllDataDTO>> getBetweenDates(@Valid @RequestParam("start_date") Date startDate,@Valid @RequestParam("end_date") Date endDate){
        return new ResponseEntity<>(userVaccineService.getAllUserByDateRange(startDate,endDate), HttpStatus.OK);
    }



}
