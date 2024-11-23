package com.rawen.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // Utilisé pour la connexion dans le frontend

    @Column(nullable = false)
    private String password; // Le mot de passe devra être sécurisé (hachage)

    @Enumerated(EnumType.STRING)
    private Role role; // Définir les rôles disponibles
}
