package com.example.challengue.Services;

import com.developer.kruger.DTO.VaccineDTO;
import com.developer.kruger.Entities.Vaccine;
import com.developer.kruger.Exception.ResourceNotFoundException;
import com.developer.kruger.Repositories.RepositoryVaccine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImpl implements IVaccineService{

    @Autowired
    private RepositoryVaccine respositoryVaccine;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VaccineDTO createVaccine(VaccineDTO vaccineDTO) {
        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineName(vaccineDTO.getNameVaccine());
        return mapVaccineToVaccineDTO(respositoryVaccine.save(vaccine));
    }

    @Override
    public VaccineDTO updateVaccine(VaccineDTO vaccineDTO, Integer idVaccine) {
        Vaccine vaccine = respositoryVaccine.findById(idVaccine)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine", "vaccineId", idVaccine));
        vaccine.setVaccineName(vaccineDTO.getNameVaccine());
        return mapVaccineToVaccineDTO(respositoryVaccine.save(vaccine));
    }

    @Override
    public List<VaccineDTO> allVaccines() {
        return  respositoryVaccine.findAll()
                .stream()
                .map(vaccine -> mapVaccineToVaccineDTO(vaccine))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteVaccineById(Integer idVaccine) {
        Vaccine vaccine = respositoryVaccine.findById(idVaccine)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine", "vaccineId", idVaccine));
        respositoryVaccine.delete(vaccine);
    }

    @Override
    public Vaccine getVaccineByVaccineName(String vaccineName) {
        return respositoryVaccine.findByNameVaccine(vaccineName)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine", "vaccineName", vaccineName));

    }


    /**
     * This method map a object and returns the VaccineDTO (Data Transfer Object)
     * @param vaccine The object to be maped.
     * @return VaccineDTO object mapped.
     */
    private VaccineDTO mapVaccineToVaccineDTO (Vaccine vaccine){
        return modelMapper.map(vaccine, VaccineDTO.class);
    }

}
