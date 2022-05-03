package com.example.challengue.Services;

import com.example.challengue.DTO.VaccineDTO;
import com.example.challengue.Entities.Vaccine;

import java.util.List;

public interface IVaccineService {

    public VaccineDTO createVaccine(VaccineDTO vaccineDTO);

    public VaccineDTO updateVaccine(VaccineDTO vaccineDTO,Integer idVaccine);

    public List<VaccineDTO> allVaccines();

    public void deleteVaccineById(Integer idVaccine);

    public Vaccine getVaccineByVaccineName(String vaccineName);

    public Vaccine getVaccineById(Integer vaccineId);

    public VaccineDTO getVaccineByIdDTO(Integer vaccineId);

}
