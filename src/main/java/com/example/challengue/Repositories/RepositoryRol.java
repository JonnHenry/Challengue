package com.example.challengue.Repositories;

import com.developer.kruger.Entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryRol extends JpaRepository<Rol,Integer> {

    public Optional<Rol> findByRol(String rol);

    public Optional<Rol> findByidRol(Integer idRol);

    public List<Rol> findByRolAndIsActive(String rol, boolean isActive);

}
