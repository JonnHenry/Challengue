package com.example.challengue.Controllers;


import com.example.challengue.DTO.Request.CreateRolDTO;
import com.example.challengue.DTO.RolDTO;
import com.example.challengue.Services.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    private IRolService rolService;

    @PostMapping
    public ResponseEntity<RolDTO> createRol(@Valid @RequestBody RolDTO rolDTO){
        return new ResponseEntity<>(rolService.createRol(rolDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> updateRol(@Valid @PathVariable(name = "id") Integer id,@Valid @RequestBody RolDTO rolDTO){
        return new ResponseEntity<>(rolService.updateRol(rolDTO,id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RolDTO>> getAllRol(){
        return new ResponseEntity<>(rolService.allRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> getRol(@Valid @PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(rolService.searchRolById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRol(@Valid @PathVariable(name = "id") Integer id){
        rolService.deleteRolById(id);
        return new ResponseEntity<>("El Rol ha sido eliminado correctamente", HttpStatus.OK);
    }




}
