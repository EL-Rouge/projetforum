package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Personne;
import com.iset.silkroad2.entities.Question;
import com.iset.silkroad2.entities.Reponse;
import com.iset.silkroad2.repository.PersonneRepository;
import com.iset.silkroad2.repository.QuestionRepository;
import com.iset.silkroad2.repository.ReponseRepository;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reponse")
public class ReponseController {

    @Autowired
    ReponseRepository reponseRepository;
    @Autowired
    PersonneRepository personnerepository;
    @Autowired
    QuestionRepository questionrepository;

    @GetMapping("")
    public List<Reponse> getallreponse(){
        return reponseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reponse getreponsebyid(@PathVariable long id){
        return reponseRepository.findById(id);
    }

    @PostMapping("/{questionId}/answer")
    public ResponseEntity<Reponse> addAnswerToQuestion(@PathVariable Long questionId, @RequestBody Reponse reponse, Principal principal) {
        Optional<Question> optionalQuestion = Optional.ofNullable(questionrepository.findById(questionId));
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            String username =principal.getName();
            Personne personne=personnerepository.findByNom(username);
            reponse.setPersonne(personne);
            reponse.setQuestion(question);
            reponse.setCreatedat(new Date());
            Reponse savedAnswer = reponseRepository.save(reponse);
            return ResponseEntity.ok(savedAnswer);
        } else {
            return ResponseEntity.notFound().build();
        }
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
