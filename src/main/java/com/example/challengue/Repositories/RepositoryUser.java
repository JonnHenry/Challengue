package com.example.challengue.Repositories;

import com.example.challengue.Entities.Rol;
import com.example.challengue.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryUser extends JpaRepository<User,String> {

    public Optional<User> findById(String id);

    public Optional<User> findByUserName(String userName);


    public Boolean existsByUserName(String username); //

    public Boolean existsByEmail(String email); //


    public List<User> findAllByUserName(String userName);

    public List<User> findAll();


}
