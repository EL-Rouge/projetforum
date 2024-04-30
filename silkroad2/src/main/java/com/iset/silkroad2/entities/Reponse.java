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

public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)

    private String contenua;

    private Date createdat;

    @ManyToOne
    @JoinColumn(name = "Personne_id")
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "Question_id")
    private Question question;
}
