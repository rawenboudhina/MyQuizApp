package com.rawen.service;


import com.rawen.models.User;
import com.rawen.models.Role;
import com.rawen.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    // Inscription d'un nouvel utilisateur
    public String registerUser(User user) {
        // Vérifier si l'utilisateur existe déjà
        Optional<User> existingUser = userRepo.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return "Nom d'utilisateur déjà pris !";
        }

        // Si le rôle n'est pas spécifié, on le définit par défaut sur "USER"
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        // Enregistrer l'utilisateur dans la base de données
        userRepo.save(user);
        return "Inscription réussie !";
    }

    // Connexion de l'utilisateur
    public String loginUser(String username, String password) {
        // Vérifier si l'utilisateur existe dans la base de données
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            return "Nom d'utilisateur ou mot de passe incorrect !";
        }

        // Vérifier si l'utilisateur est un admin
        boolean isAdmin = user.get().getRole() == Role.ADMIN;

        // Retourner un message en fonction du rôle
        return isAdmin ? "Bienvenue, Admin !" : "Connexion réussie, Utilisateur.";
    }
}
