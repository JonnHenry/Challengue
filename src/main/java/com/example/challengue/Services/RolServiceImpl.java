package com.example.challengue.Services;

import com.developer.kruger.DTO.RolDTO;
import com.developer.kruger.Entities.Rol;
import com.developer.kruger.Exception.ResourceNotFoundException;
import com.developer.kruger.Repositories.RepositoryRol;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements IRolService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RepositoryRol repositoryRol;

    /**
     * This method update a rol by the Rol and idRol
     * @param rolDTO The object to create
     * @return RolDTO or an exception if resource not found.
     */
    @Override
    public RolDTO createRol(RolDTO rolDTO) {
        Rol rol = mapeEntity(rolDTO);
        RolDTO responseRol = mapDTO(repositoryRol.save(rol));
        return responseRol;
    }


    /**
     * This method update a rol by the Rol and idRol
     * @param rolDTO The values to update.
     * @param idRol The id to search.
     * @return RolDTO or an exception if resource not found.
     */
    @Override
    public RolDTO updateRol(RolDTO rolDTO, Integer idRol) {
        Rol rol = repositoryRol.findByidRol(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "idRol", idRol));

        return mapDTO(rol);
    }

    /**
     * This method list all roles
     * @return List<RolDTO> or an exception if resource not found.
     */
    @Override
    public List<RolDTO> allRoles() {
        List<Rol> listRol = repositoryRol.findAll();
        return  listRol.stream().map(rol->mapDTO(rol))
                .collect(Collectors.toList());
    }

    /**
     * This method search a rol by id
     * @param idRol The value to search.
     * @return RolDTO or an exception if resource not found.
     */
    @Override
    public RolDTO searchRolById(Integer idRol) {
        Rol rol = repositoryRol.findByidRol(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "idRol", idRol));

        return mapDTO(rol);
    }


    /**
     * This method search a rol by nameRol
     * @param nameRol The value to search.
     * @return RolDTO or an exception if resource not found.
     */
    @Override
    public RolDTO searchRolByNameRol(String nameRol) {
        Rol rol = repositoryRol.findByRol(nameRol)
                        .orElseThrow(() -> new ResourceNotFoundException("Rol", "idRol", nameRol));

        return mapDTO(rol);
    }

    /**
     * This method delete a rol by roleId
     * @param roleId The object to be maped.
     * @return An exception if resource not found.
     */
    @Override
    public void deleteRolById(Integer roleId) {
        Rol rol = repositoryRol.findByidRol(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "idRol", roleId));
        repositoryRol.delete(rol);
    }

    /**
     * This method delete a rol by nameRol
     * @param nameRol The object to be maped.
     * @return An exception if resource not found.
     */
    @Override
    public void deleteRolByNameRol(String nameRol) {
        Rol rol = repositoryRol.findByRol(nameRol)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "idRol", nameRol));
        repositoryRol.delete(rol);
    }


    /**
     * This method map a object and returns the RolDTO Data Transfer Object
     * @param rol The object to be maped.
     * @return RolDTO object mapped.
     */
    private RolDTO mapDTO(Rol rol) {
        RolDTO publicacionDTO = modelMapper.map(rol, RolDTO.class);
        return publicacionDTO;
    }

    /**
     * This method returns the rol entity mapped
     * @param rolDTO The object to be maped.
     * @return Rol object mapped.
     */
    private Rol mapeEntity(RolDTO rolDTO) {
        Rol rol = modelMapper.map(rolDTO, Rol.class);
        return rol;
    }
}

