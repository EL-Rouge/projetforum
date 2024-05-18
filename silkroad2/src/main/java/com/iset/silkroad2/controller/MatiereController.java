package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Chapitre;
import com.iset.silkroad2.entities.Cour;
import com.iset.silkroad2.entities.Matiere;
import com.iset.silkroad2.repository.ChapitreRepository;
import com.iset.silkroad2.repository.CourRepository;
import com.iset.silkroad2.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/matieres")
public class MatiereController {

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private ChapitreRepository chapitreRepository;

    @Autowired
    private CourRepository courRepository;


    @GetMapping("/matieres")
    public String listMatieres(Model model) {
        List<Matiere> matieres = matiereRepository.findAll();
        model.addAttribute("matieres", matieres);
        return "/Matiere/allmatiere";
    }



    @GetMapping("/add")
    public String showAddMatiereForm(Model model) {
        model.addAttribute("matiere", new Matiere());
        return "Matiere/ajouter";
    }

    @PostMapping("/add")
    public String addMatiere(@RequestParam("titrem") String titrem) {
        Matiere newMatiere = new Matiere();
        newMatiere.setTitrem(titrem);
        matiereRepository.save(newMatiere);
        return "redirect:/matieres/matieres"; // Redirect to the page showing the list of matieres
    }

    @DeleteMapping("/delete/{matiereId}")
    public String deleteMatiere(@PathVariable Long matiereId) {
        matiereRepository.deleteById(matiereId);
        return "redirect:/matieres/matieres";
    }

    @DeleteMapping("/chapitres/delete/{chapitreId}")
    public String deleteChapitre(@PathVariable Long chapitreId) {
        chapitreRepository.deleteById(chapitreId);
        return "redirect:/matieres/matieres";
    }



    // Add similar methods for updating and deleting Chapitres and Cours
}

