package com.iset.silkroad2.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "niveau_etude")
    private String niveau_etude;

    @Column(name = "date_naissance")
    private Date date_naissance;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL)
    private List<Reponse> reponses;

}
