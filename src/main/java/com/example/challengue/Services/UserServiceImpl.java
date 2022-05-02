package com.example.challengue.Services;

import com.developer.kruger.DTO.CreateUserDTO;
import com.developer.kruger.DTO.UserRegisterDTO;
import com.developer.kruger.Entities.Rol;
import com.developer.kruger.Entities.User;
import com.developer.kruger.Exception.ResourceNotFoundException;
import com.developer.kruger.Repositories.RepositoryUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public CreateUserDTO createUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setId(userRegisterDTO.getIdUser());
        user.setNames(userRegisterDTO.getNames());
        user.setSurnames(userRegisterDTO.getSurnames());
        user.setEmail(userRegisterDTO.getEmail());
        user.setRoles(Arrays.asList(new Rol("ROLE_USER"))); //By defult to all users
        user.setUserName(generateUserName(userRegisterDTO.getEmail()));
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getIdUser()));
        User newUser = repositoryUser.save(user);
        newUser.setPassword(userRegisterDTO.getIdUser());
        return  mapUserToCreateUserDTO(newUser);
    }

    @Override
    public UserRegisterDTO updateCreateUser(UserRegisterDTO userRegisterDTO, String userId) {
        User user = repositoryUser.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "idUser", userId));
        user.setUserName(userRegisterDTO.getNames());
        user.setSurnames(userRegisterDTO.getSurnames());
        user.setEmail(userRegisterDTO.getEmail());
        User userUpdated = repositoryUser.save(user);
        return mapUserToCUserRegisterDTO(userUpdated);
    }

    @Override
    public void deleteUserCreatedById(String userId) {
        User user = repositoryUser.findByUserId(userId)
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
    public User completeUserRegistration(String username, Date birthDate, String address, String telephone) {
        User user = repositoryUser.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userName", username));
        user.setBirthDate(birthDate);
        user.setAddress(address);
        user.setTelephone(telephone);
        return repositoryUser.save(user);
    }


    /**
     * This method map the roles of a user in a collection
     * @param roles The collection of a Rol.
     * @return Collection<GrantedAuthority>
     */
    private Collection<? extends GrantedAuthority> mapRolAuthority(Collection<Rol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRol())).collect(Collectors.toList());
    }

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
     * This method map a object and returns the UserRegisterDTO (Data Transfer Object)
     * @param user The object to be maped.
     * @return UserRegisterDTO object mapped.
     */
    private UserRegisterDTO mapUserToCUserRegisterDTO(User user) {
        UserRegisterDTO userRegisterDTO = modelMapper.map(user, UserRegisterDTO.class);
        return userRegisterDTO;
    }


    /**
     * This method is to load a user by Username and return a UserDetail object of spring security
     * @param username The object to be maped.
     * @return UserDetails object mapped.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repositoryUser.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario o password inválidos"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolAuthority(user.getRoles()));
    }

    private Integer numUserBySameUserName(String userName){
        List<User> listUser = repositoryUser.findAllByUserName(userName);
        return listUser.size();
    }

    private String generateUserName(String email){
        String userName = email.substring(0,email.indexOf("@"));
        Integer numUsersBySameName = numUserBySameUserName(userName)+1;
        if (numUsersBySameName==1){
            return userName;
        }else{
            return userName+numUsersBySameName.toString();
        }
    }





}
