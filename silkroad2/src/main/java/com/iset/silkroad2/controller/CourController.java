package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Cour;
import com.iset.silkroad2.repository.CourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cours")
public class CourController {

    @Autowired
    private CourRepository courRepository;

    @GetMapping("/add")
    public String showAddCourForm(Model model) {
        model.addAttribute("cour", new Cour());
        return "add-cour";
    }

    @PostMapping("/add")
    public String addCour(@ModelAttribute("cour") Cour cour) {
        courRepository.save(cour);
        return "redirect:/matieres/matieres";
    }

    @DeleteMapping("/delete/{courid}")
    public String deleteChapter(@PathVariable Long courid) {
        courRepository.deleteById(courid);

        return "redirect:/matieres/matieres";
    }

    // Add methods for updating and deleting Cours
}

