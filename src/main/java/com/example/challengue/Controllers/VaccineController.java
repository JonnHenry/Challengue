package com.example.challengue.Controllers;

import com.example.challengue.DTO.VaccineDTO;
import com.example.challengue.Services.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/vaccine")
public class VaccineController {


    @Autowired
    private IVaccineService vaccineService;

    @PostMapping()
    public ResponseEntity<VaccineDTO> createVaccine(@Valid @RequestBody VaccineDTO vaccineDTO){
        return new ResponseEntity<>(vaccineService.createVaccine(vaccineDTO), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<VaccineDTO> updateVaccine(@Valid @PathVariable(name = "id") Integer id, @Valid @RequestBody VaccineDTO vaccineDTO){
        return new ResponseEntity<>(vaccineService.updateVaccine(vaccineDTO,id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VaccineDTO>> getAllVaccine(){
        return new ResponseEntity<>(vaccineService.allVaccines(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccineDTO> getByIdVaccine(Integer id){
        return new ResponseEntity<>(vaccineService.getVaccineByIdDTO(id), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByIdVaccine(Integer id){
        vaccineService.deleteVaccineById(id);
        return new ResponseEntity<>("Vacuna eliminada exitosamente", HttpStatus.OK);
    }
}
