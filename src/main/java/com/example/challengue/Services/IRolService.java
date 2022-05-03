package com.example.challengue.Services;


import com.example.challengue.DTO.RolDTO;

import java.util.List;

public interface IRolService {

    public RolDTO createRol(RolDTO rolDTO);

    public RolDTO updateRol(RolDTO rolDTO,Integer updateRol);

    public List<RolDTO> allRoles();

    public RolDTO searchRolById(Integer id);

    public RolDTO searchRolByNameRol(String nameRol);

    public void deleteRolById(Integer id);

    public void deleteRolByNameRol(String nameRol);

}
