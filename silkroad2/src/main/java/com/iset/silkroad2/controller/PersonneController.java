package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Personne;
import com.iset.silkroad2.repository.PersonneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personnes")
public class PersonneController {

    @Autowired
    PersonneRepository personnerepository;

    @GetMapping("")
    public List<Personne> getAllPersonnes() {
        return personnerepository.findAll();
    }

    @GetMapping("/{id}")
    public Personne getPersonneById(@PathVariable Long id) {
        return personnerepository.findById(id);
    }

    @PostMapping("/add")
    public Personne createPersonne(@RequestBody Personne personne) {
        return personnerepository.save(personne);
    }

    @PutMapping("/modifier/{id}")
    public Personne modiferpersonne(@PathVariable Long id, @RequestBody Personne personne) {
        Personne personne1 = personnerepository.findById(id);
        personne1.setNom(personne.getNom());
        personne1.setPrenom(personne.getPrenom());
        personne1.setEmail(personne.getEmail());
        personne1.setTelephone(personne.getTelephone());
        return personnerepository.save(personne1);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletepersonne(@PathVariable Long id) {
        personnerepository.deleteById(id);
    }

}
