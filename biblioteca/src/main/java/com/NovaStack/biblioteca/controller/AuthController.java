package com.NovaStack.biblioteca.controller;


import com.NovaStack.biblioteca.dto.auth.LoginRequestDTO;
import com.NovaStack.biblioteca.dto.auth.RegisterRequestDTO;
import com.NovaStack.biblioteca.dto.auth.UserResponseDTO;
import com.NovaStack.biblioteca.infra.security.TokenService;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto){
        User user = this.repository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("user not found"));
        if (passwordEncoder.matches(dto.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new UserResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().body("user not found");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO dto){
        Optional<User> userOptional = this.repository.findByEmail(dto.email());
        if(userOptional.isEmpty()){
            User user = new User(dto.name(), dto.email(), passwordEncoder.encode(dto.password()));
            this.repository.save(user);
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new UserResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}


