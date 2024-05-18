package com.iset.silkroad2.entities;

import jakarta.persistence.*;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "chapitre_id")
    private Chapitre chapitre;

}
