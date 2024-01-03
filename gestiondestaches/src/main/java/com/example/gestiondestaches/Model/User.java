package com.example.gestiondestaches.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long User_id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Task>tasks;
}
