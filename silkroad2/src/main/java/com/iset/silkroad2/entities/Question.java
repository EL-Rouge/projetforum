package com.iset.silkroad2.entities;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titreq;

    @Column(nullable = false)
    private String contenuq;

    private int jaime   ;

    private int dislike;

    private int nbanswers;

    private boolean reported;

    private Date createdatQ;


    @Enumerated(EnumType.STRING)
    private Tags tag;


    @ManyToOne
    @JoinColumn(name = "personne_id")
    private Personne personne;

    @OneToMany(mappedBy = "question")
    private List<Reponse> reponse;

}

