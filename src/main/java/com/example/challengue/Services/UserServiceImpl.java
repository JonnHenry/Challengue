package com.example.challengue.Services;

import com.example.challengue.DTO.Response.CreateUserDTO;
import com.example.challengue.DTO.Response.UserAllDataDTO;
import com.example.challengue.DTO.UserRegisterDTO;
import com.example.challengue.Entities.Rol;
import com.example.challengue.Entities.User;
import com.example.challengue.Exception.ResourceNotFoundException;
import com.example.challengue.Repositories.RepositoryRol;
import com.example.challengue.Repositories.RepositoryUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private RepositoryRol repositoryRol;

    /*
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;*/



    @Override
    public UserRegisterDTO updateCreateUser(UserRegisterDTO userRegisterDTO, String userId) {
        User user = repositoryUser.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "idUser", userId));
        user.setUserName(userRegisterDTO.getNames());
        user.setSurnames(userRegisterDTO.getSurnames());
        user.setEmail(userRegisterDTO.getEmail());
        User userUpdated = repositoryUser.save(user);
        return mapUserToCUserRegisterDTO(userUpdated);
    }

    @Override
    public void deleteUserCreatedById(String userId) {
        User user = repositoryUser.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "idUser", userId));
        repositoryUser.delete(user);
    }

    /**
     * This method is to complete the process of the register the data of the user
     * @param username String
     * @param birthDate Date
     * @param address String
     * @param telephone String
     * @return user Use
     */
    @Override
    public UserAllDataDTO completeUserRegistration(String username, Date birthDate, String address, String telephone) {
        User user = repositoryUser.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userName", username));
        user.setBirthDate(birthDate);
        user.setAddress(address);
        user.setTelephone(telephone);
        return mapUserToUserAllDataDTO(repositoryUser.save(user));
    }

    @Override
    public User findUserByUsername(String username) {
        User user = repositoryUser.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userName", username));
        return user;
    }

    @Override
    public List<UserAllDataDTO> getAllUsers() {
        List<UserAllDataDTO> users = repositoryUser.findAll()
                .stream()
                .map(user -> mapUserToUserAllDataDTO(user))
                .collect(Collectors.toList());

        return users;
    }

    @Override
    public UserAllDataDTO getUserById(String id) {
        User user = repositoryUser.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return mapUserToUserAllDataDTO(user);
    }

    @Override
    public List<Rol> getAllRolesByUsername(String username) {
        User user = repositoryUser.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userName", username));
        return new ArrayList<>(repositoryUser.save(user).getRoles());
    }

    @Override
    public List<Rol> appendRolByUsernameAndIdRol(String username,Integer idRol) {
        User user = repositoryUser.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userName", username));
        Rol rol = repositoryRol.findById(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "idRol", idRol));
        var listRoles = (Set<Rol>) user.getRoles();
        listRoles.add(rol);
        user.setRoles(listRoles);

        return new ArrayList<>(repositoryUser.save(user).getRoles());
    }


    /**
     * This method map the roles of a user in a collection
     * @param roles The collection of a Rol.
     * @return Collection<GrantedAuthority>
     */
    /*
    private Collection<? extends GrantedAuthority> mapRolAuthority(Collection<Rol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRol())).collect(Collectors.toList());
    } */

    /**
     * This method map a object and returns the CreateUserDTO (Data Transfer Object)
     * @param registerDTO The object to be maped.
     * @return CreateUserDTO object mapped.
     */
    private CreateUserDTO mapUserRegisterDTOToCreateUserDTO(UserRegisterDTO registerDTO) {
        CreateUserDTO createUserDTO = modelMapper.map(registerDTO, CreateUserDTO.class);
        return createUserDTO;
    }


    /**
     * This method map a object and returns the CreateUserDTO (Data Transfer Object)
     * @param user The object to be maped.
     * @return CreateUserDTO object mapped.
     */
    private CreateUserDTO mapUserToCreateUserDTO(User user) {
        CreateUserDTO createUserDTO = modelMapper.map(user, CreateUserDTO.class);
        return createUserDTO;
    }


    /**
     * This method map a object and returns the CreateUserDTO (Data Transfer Object)
     * @param user The object to be maped.
     * @return CreateUserDTO object mapped.
     */
    private UserAllDataDTO mapUserToUserAllDataDTO(User user) {

        return  modelMapper.map(user, UserAllDataDTO.class);
    }

    /**
     * This method map a object and returns the UserRegisterDTO (Data Transfer Object)
     * @param user The object to be maped.
     * @return UserRegisterDTO object mapped.
     */
    private UserRegisterDTO mapUserToCUserRegisterDTO(User user) {
        UserRegisterDTO userRegisterDTO = modelMapper.map(user, UserRegisterDTO.class);
        return userRegisterDTO;
    }

    /*
    /**
     * This method is to load a user by Username and return a UserDetail object of spring security
     * @param username The object to be maped.
     * @return UserDetails object mapped.
     */

    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repositoryUser.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario o password inválidos"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolAuthority(user.getRoles()));
    }*/

    private Integer numUserBySameUserName(String userName){
        List<User> listUser = repositoryUser.findAllByUserName(userName);
        return listUser.size();
    }

    private String generateUserName(String email){
        String userName = email.trim().toLowerCase().substring(0,email.indexOf("@"));

        Integer numUsersBySameName = numUserBySameUserName(userName)+1;
        if (numUsersBySameName==1){
            return userName;
        }else{
            return userName+numUsersBySameName.toString();
        }
    }





}
