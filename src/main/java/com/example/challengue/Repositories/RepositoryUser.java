package com.example.challengue.Repositories;

import com.developer.kruger.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryUser extends JpaRepository<User,String> {

    public Optional<User> findByUserId(String id);

    public Optional<User> findByUserName(String userName);

    public List<User> findByUserIdAndIsActive(String id);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);

    public List<User> findAllByUserName(String userName);

}
