package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Question;
import com.iset.silkroad2.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;


@RestController
@RequestMapping("/api")
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;

    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question) {

        return questionRepository.save(question);
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


