package com.iset.silkroad2.controller;


import com.iset.silkroad2.entities.Personne;
import com.iset.silkroad2.entities.Question;
import com.iset.silkroad2.entities.Reponse;
import com.iset.silkroad2.entities.Tags;
import com.iset.silkroad2.repository.PersonneRepository;
import com.iset.silkroad2.repository.QuestionRepository;
import com.iset.silkroad2.repository.ReponseRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;


import javax.swing.*;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ReponseRepository  reponseRepository; // Corrected the variable name
    @Autowired
    PersonneRepository personneRepository; // Corrected the variable name

    @GetMapping("/ask")
    public String showAskQuestionForm(Model model, @RequestParam(name = "tag", required = false, defaultValue = "Restorant") Tags tag) {
        List<Question> questions = questionRepository.findAll();//
        model.addAttribute("question", new Question());
        model.addAttribute("tags", Tags.values());

        model.addAttribute("questions", questions);//

        return "Question/question";
    }


    @GetMapping("/question/{id}/edit")
    public String showEditQuestionForm(@PathVariable Long id, Model model) {
        Question question = questionRepository.findById(id);
        List<Question> questions = questionRepository.findAll();//
        model.addAttribute("question", question);
        model.addAttribute("tags", Tags.values());

        model.addAttribute("questions", questions);//

        return "Question/edit_question";
    }
        @GetMapping("/all")
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
   }

    @GetMapping("/index")
    public String showIndex(){
        return "header,footer/header";
    }



    @PostMapping("/add")
    public String addQuestion(@ModelAttribute Question question, Principal principal) {
        // Get the current authenticated user's ID
        String username = principal.getName();
        Personne personne = personneRepository.findByNom(username);
        Long userId = personne.getId();

        question.getPersonne().setId(userId);
        question.setCreatedatQ(new Date());

        questionRepository.save(question);
        return "redirect:/index";
    }

    @PostMapping("/hi")
    public String addQuestionn(@ModelAttribute("question") Question question) {
        question.setCreatedatQ(new Date());
        questionRepository.save(question);
        return "redirect:/question/questions";
    }












    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionRepository.findById(id);
    }





//    @DeleteMapping("/{id}")
//    @Transactional
//    public void deleteQuestion(@PathVariable Long id) throws NotFoundException {
//        Optional<Question> optionalQuestion = Optional.ofNullable(questionRepository.findById(id));
//        if (optionalQuestion.isPresent()) {
//            Question question = optionalQuestion.get();
//            // Delete related responses
//            List<Reponse> responses = reponseRepository.findByQuestion(question);
//            reponseRepository.deleteAll(responses);
//            // Delete the question
//            questionRepository.deleteById(id);
//        } else {
//            throw new NotFoundException("Question not found");
//        }
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public String deleteQuestion(@PathVariable Long id, Model model) {
        Optional<Question> optionalQuestion = Optional.ofNullable(questionRepository.findById(id));
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            // Delete related responses
            List<Reponse> responses = reponseRepository.findByQuestion(question);
            reponseRepository.deleteAll(responses);
            // Delete the question
            questionRepository.deleteById(id);
            return "redirect:/question/questions"; // Redirect to home after successful deletion
        } else {
            model.addAttribute("error", "Question not found");
            return "error/404"; // Or another appropriate error page
        }
    }

    @PutMapping("/modifier/{id}")
    public String modiferquestion(@PathVariable Long id, @ModelAttribute("question") Question question) {
        Question question1 = questionRepository.findById(id);
        if (question1 != null) {
            question1.setTitreq(question.getTitreq());
            question1.setContenuq(question.getContenuq());
            questionRepository.save(question1);
            return "redirect:/question/index";
        } else {
            // Handle the case where the question with the given ID is not found
            return "redirect:/error";
        }
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Question> modifyQuestion(@RequestBody Question question, @PathVariable Long id) {
        Optional<Question> optionalQuestion = Optional.ofNullable(questionRepository.findById(id));
        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setTitreq(question.getTitreq());
            existingQuestion.setContenuq(question.getContenuq());
            Question updatedQuestion = questionRepository.save(existingQuestion);
            return ResponseEntity.ok(updatedQuestion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/questions/search")
    public String searchByTitle(@RequestParam String titreq, Model model) {
        List<Question> questions = questionRepository.findByTitreqContaining(titreq);
        model.addAttribute("questions", questions);
        return "home/home"; // Assuming this is the name of your Thymeleaf template
    }



    @GetMapping("/questions")
    public String list(Model model) {
        List<Question> questions = questionRepository.findAll(); // Implement this method in your service
        model.addAttribute("questions", questions);
        return "home/home";
    }



    //    signaler
    @PostMapping("/signal/{questionId}")
    public ResponseEntity<?> signalQuestion(@PathVariable Long questionId) {
        Optional<Question> optionalQuestion = Optional.ofNullable(questionRepository.findById(questionId));
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            question.setReported(true);
            questionRepository.save(question);
            return ResponseEntity.ok(question);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}/like")
    public ResponseEntity<Question> likeQuestion(@PathVariable Long id, @RequestBody Map<String, Boolean> payload) {
        Optional<Question> optionalQuestion = Optional.ofNullable(questionRepository.findById(id));
        if (!optionalQuestion.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Question question = optionalQuestion.get();
        if (payload.get("increment")) {
            question.setJaime(question.getJaime() + 1);
        } else {
            question.setDislike(question.getDislike() + 1);
        }

        questionRepository.save(question);
        return ResponseEntity.ok(question);
    }

}










