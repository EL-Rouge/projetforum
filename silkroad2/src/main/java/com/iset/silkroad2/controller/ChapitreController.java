package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Chapitre;
import com.iset.silkroad2.repository.ChapitreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chapitres")
public class ChapitreController {

    @Autowired
    private ChapitreRepository chapitreRepository;

    @GetMapping("/add")
    public String showAddChapitreForm(Model model) {
        model.addAttribute("chapitre", new Chapitre());
        return "add-chapitre";
    }

    @PostMapping("/add")
    public String addChapitre(@ModelAttribute("chapitre") Chapitre chapitre) {
        chapitreRepository.save(chapitre);
        return "redirect:/chapitres/add";
    }

    // Add methods for updating and deleting Chapitres
}
