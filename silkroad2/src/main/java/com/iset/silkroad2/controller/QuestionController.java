package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Personne;
import com.iset.silkroad2.entities.Question;
import com.iset.silkroad2.repository.PersonneRepository;
import com.iset.silkroad2.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;


import javax.swing.*;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;


    @Autowired
    PersonneRepository personneRepository; // Corrected the variable name

    @GetMapping("/ask")
    public String showAskQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "question";
    }

    @GetMapping("/index")
    public String showIndex(){
        return "header,footer/index";
    }



    @PostMapping("/add")
    public String addQuestion(@ModelAttribute Question question, Principal principal) {
        // Get the current authenticated user's ID
        String username = principal.getName();
        Personne personne = personneRepository.findByNom(username);
        Long userId = personne.getId();

        // Set the current user's ID and creation date
        question.getPersonne().setId(userId);
        question.setCreatedatQ(new Date());

        questionRepository.save(question);
        return "redirect:/index"; // Redirect to the home page after adding the question
    }

    @PostMapping("/hi")
    public String addQuestionn(@ModelAttribute("question") Question question) {
        questionRepository.save(question);
        return "redirect:/index"; // Redirect to the home page after adding the question
    }



//    signaler
    @PostMapping("/signal/{id}")
    public ResponseEntity<Question> signalerQuestion(@PathVariable Long id) {
        Optional<Question> optionalQuestion = Optional.ofNullable(questionRepository.findById(id));
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            question.setReported(true);
            questionRepository.save(question);
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    @GetMapping("")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }


    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionRepository.findById(id);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public void deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
    }
    @PutMapping("/modifier/{id}")
    public Question modiferquestion(@RequestBody Question question , @PathVariable Long id ){
        Question question1 = questionRepository.findById(id);
        question1.setTitreq(question.getTitreq());
        question1.setContenuq(question.getContenuq());
        return questionRepository.save(question1);
    }



}


