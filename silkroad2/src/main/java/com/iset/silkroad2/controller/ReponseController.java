package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Reponse;
import com.iset.silkroad2.repository.ReponseRepository;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reponse")
public class ReponseController {

    @Autowired
    ReponseRepository reponseRepository;

    @GetMapping("")
    public List<Reponse> getallreponse(){
        return reponseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reponse getreponsebyid(@PathVariable long id){
        return reponseRepository.findById(id);
    }

    @PostMapping("/add")
    public Reponse addreponse(@RequestBody Reponse reponse){
        return reponseRepository.save(reponse);
    }

    @PutMapping("/modifier/{id}")
    public Reponse modifierreponse(@RequestBody Reponse reponse,@PathVariable Long id){
        Reponse reponse1=reponseRepository.findById(id);
        reponse1.setContenua(reponse.getContenua());

        return reponseRepository.save(reponse1);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletereponse(@PathVariable Long id){
        reponseRepository.deleteById(id);
    }



}
