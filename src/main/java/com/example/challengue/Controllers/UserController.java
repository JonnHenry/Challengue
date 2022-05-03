package com.example.challengue.Controllers;


import com.example.challengue.DTO.Request.CreateRolDTO;
import com.example.challengue.DTO.Response.CreateUserDTO;
import com.example.challengue.DTO.Response.UserAllDataDTO;
import com.example.challengue.DTO.Response.UserCompleteRegisterDTO;
import com.example.challengue.DTO.RolDTO;
import com.example.challengue.DTO.UserRegisterDTO;

import com.example.challengue.Entities.Rol;
import com.example.challengue.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        return new ResponseEntity<>(userService.createUser(userRegisterDTO), HttpStatus.CREATED);
    }

    @PostMapping("/complete_registry")
    public ResponseEntity<UserAllDataDTO> completeProcessRegistration(@Valid @RequestBody UserCompleteRegisterDTO userCompleteRegisterDTO){
        return new ResponseEntity<>(
                userService.completeUserRegistration(
                        userCompleteRegisterDTO.getUsername(),
                        userCompleteRegisterDTO.getBirthDate(),
                        userCompleteRegisterDTO.getAddress(),
                        userCompleteRegisterDTO.getTelephone()),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRegisterDTO> updateUser(@Valid @PathVariable(name = "id") String id, @Valid @RequestBody UserRegisterDTO userRegisterDTO){
        return new ResponseEntity<>(userService.updateCreateUser(userRegisterDTO,id), HttpStatus.OK);
    }

    @PutMapping("/complete_registry/{username}")
    public ResponseEntity<UserAllDataDTO> completeProcessRegistration(@Valid @PathVariable(name = "username") String username,@Valid @RequestBody UserCompleteRegisterDTO userCompleteRegisterDTO){
        return new ResponseEntity<>(
                userService.completeUserRegistration(
                        username,
                        userCompleteRegisterDTO.getBirthDate(),
                        userCompleteRegisterDTO.getAddress(),
                        userCompleteRegisterDTO.getTelephone()),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserAllDataDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAllDataDTO> getUserById(@Valid @PathVariable(name = "id") String id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@Valid @PathVariable(name = "id") String id){
        userService.deleteUserCreatedById(id);
        return new ResponseEntity<>("Usurio: "+ id +" eliminado exitosamente", HttpStatus.OK);
    }

    @PostMapping("/rol")
    public ResponseEntity<List<Rol>> appendRol(@Valid @RequestBody CreateRolDTO createRolDTO) {
        return new ResponseEntity<>(userService.appendRolByUsernameAndIdRol(createRolDTO.getUsername(), createRolDTO.getIdRol()), HttpStatus.CREATED);
    }

    @GetMapping("/rol/{username}")
    public ResponseEntity<List<Rol>> getAlldRoles(@Valid @PathVariable(name = "username") String username ) {
        return new ResponseEntity<>(userService.getAllRolesByUsername(username), HttpStatus.OK);
    }







}
