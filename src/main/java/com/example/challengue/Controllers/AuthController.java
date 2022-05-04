package com.example.challengue.Controllers;


import com.example.challengue.DTO.LoginDTO;
import com.example.challengue.DTO.UserRegisterDTO;
import com.example.challengue.Entities.Rol;
import com.example.challengue.Entities.User;
import com.example.challengue.Exception.ResourceNotFoundException;
import com.example.challengue.Repositories.RepositoryRol;
import com.example.challengue.Repositories.RepositoryUser;
import com.example.challengue.Security.JWTAuthResonseDTO;
import com.example.challengue.Security.JwtTokenProvider;
import com.example.challengue.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.Locale;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RepositoryUser userRepository;

    @Autowired
    private RepositoryRol rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IUserService userService;


    @PostMapping("/login")
    public ResponseEntity<JWTAuthResonseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        System.out.println(loginDTO.getUsername() +": "+loginDTO.getPassword().toLowerCase(Locale.ROOT));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResonseDTO(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registrarUsuario(@RequestBody UserRegisterDTO userRegisterDTO){

        if(userRepository.existsByUserName(generateUserName(userRegisterDTO.getEmail()))) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe",HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setId(userRegisterDTO.getId());
        user.setNames(userRegisterDTO.getNames().toUpperCase());
        user.setSurnames(userRegisterDTO.getSurnames().toUpperCase());
        user.setEmail(userRegisterDTO.getEmail());
        user.setUserName(generateUserName(userRegisterDTO.getEmail()));
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getId()));
        User newUser = userRepository.save(user);
        newUser.setPassword(userRegisterDTO.getId());

        Rol roles = rolRepository.findByRol("USER")
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "rol", "USER"));
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
    }

    private String generateUserName(String email){
        String userName = email.trim().toLowerCase().substring(0,email.indexOf("@"));
        return userName;
    }

}
