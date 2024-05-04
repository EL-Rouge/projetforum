package com.iset.silkroad2.repository;

import com.iset.silkroad2.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne,Integer> {

    Personne findById(Long id);
    void deleteById(Long id);

    Personne findByNom(String nom);

}
