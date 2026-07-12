package com.assetflow.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assetflow.model.LoginRequest;
import com.assetflow.model.Role;
import com.assetflow.model.User;
import com.assetflow.repository.UserRepository;
import com.assetflow.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private PasswordEncoder encoder;
    @Autowired private UserRepository userRepo;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username, req.password));
        return jwtUtil.generateToken(req.username);
    }

    @PostMapping("/register")
    public String register(@RequestBody LoginRequest req) {
        User user = new User();
        user.setUsername(req.username);
        user.setPassword(encoder.encode(req.password));
        user.setRoles(Set.of(Role.USER));
        userRepo.save(user);
        return "User registered";
    }
}