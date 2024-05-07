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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
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

//    @GetMapping("/{id}")
//    public Reponse getreponsebyid(@PathVariable long id){
//
//        return reponseRepository.findById(id);
//    }

    @GetMapping("/{id}")
    public String getQuestionById(@PathVariable Long id, Model model) {
        Question question = questionrepository.findById(id);

        List<Reponse> reponse = question.getReponse();

        model.addAttribute("question", question);
        model.addAttribute("answers", reponse);
        model.addAttribute("reponse", new Reponse());

        return "reponse/reponse";
    }

//    editer
@GetMapping("/reponse/{id}/edit")
public String showEditResponseForm(@PathVariable Long id, Model model) {
    Reponse reponse = reponseRepository.findById(id);
    String questionTitre = reponse.getQuestion().getTitreq();
    model.addAttribute("reponse", reponse);
    model.addAttribute("questionTitre", questionTitre);
    return "reponse/edit_reponse";
}





    @PostMapping("/{questionId}/addanswer")
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
//9bal mane5dem el user


    @PutMapping("/modifier/{questionid}")
    public String addAnswerContentOnly(@PathVariable Long questionId, @ModelAttribute("reponse") Reponse reponse) {
        Optional<Question> optionalQuestion = Optional.ofNullable(questionrepository.findById(questionId));
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            Reponse existingReponse = reponseRepository.findById(reponse.getId());
            existingReponse.setQuestion(question);
            existingReponse.setContenua(reponse.getContenua());
            existingReponse.setCreatedat(new Date());
            Reponse savedAnswer = reponseRepository.save(existingReponse);
            // Redirect to the template
            return "redirect:/question/index";
        } else {
            // Handle not found case
            return "error";
        }
}

    @PutMapping("/modify/{id}")
    public String updateResponse(@PathVariable Long id, @ModelAttribute("reponse") Reponse updatedResponse) {
        Reponse existingResponse = reponseRepository.findById(id);
        existingResponse.setContenua(updatedResponse.getContenua());
        reponseRepository.save(existingResponse);
        return "redirect:/question/index";
    }





//




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
