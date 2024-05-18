package com.iset.silkroad2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chapitre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @ManyToOne
    @JoinColumn(name = "matiere_id", nullable = false)
    private Matiere matiere;

    @OneToMany(mappedBy = "chapitre", cascade = CascadeType.ALL)
    private List<Cour> cours ;

    @OneToMany(mappedBy = "chapitre", cascade = CascadeType.ALL)
    private List<Tp> tps ;

    @OneToMany(mappedBy = "chapitre", cascade = CascadeType.ALL)
    private List<Td> tds ;

    public void addTd(Td td) {
    }

    public void addTp(Tp tp) {
    }

    public void addCour(Cour cour) {
    }


    // Constructors, getters, and setters
}
