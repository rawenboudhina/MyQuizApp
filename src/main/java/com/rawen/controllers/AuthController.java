package com.rawen.controllers;

import com.rawen.models.User;
import com.rawen.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Inscription d'un utilisateur
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Utiliser le service pour l'inscription
        String response = userService.registerUser(user);
        
        // Retourner la réponse
        return ResponseEntity.ok(response);
    }

    // Connexion de l'utilisateur
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        // Utiliser le service pour la connexion
        String response = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        
        // Retourner la réponse
        return ResponseEntity.ok(response);
    }
}