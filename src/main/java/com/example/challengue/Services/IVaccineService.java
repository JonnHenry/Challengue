package com.example.challengue.Services;

import com.developer.kruger.DTO.VaccineDTO;
import com.developer.kruger.Entities.Vaccine;

import java.util.List;

public interface IVaccineService {

    public VaccineDTO createVaccine(VaccineDTO vaccineDTO);

    public VaccineDTO updateVaccine(VaccineDTO vaccineDTO,Integer idVaccine);

    public List<VaccineDTO> allVaccines();

    public void deleteVaccineById(Integer idVaccine);

    public Vaccine getVaccineByVaccineName(String vaccineName);

}
