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
    public String addMatiere(@ModelAttribute("matiere") Matiere matiere) {
        matiereRepository.save(matiere);
        return "redirect:/matieres/add";
    }

    @GetMapping("/{matiereId}/chapitres/add")
    public String showAddChapitreForm(@PathVariable Long matiereId, Model model) {
        Matiere matiere = matiereRepository.findById(matiereId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Matiere Id:" + matiereId));
        model.addAttribute("chapitre", new Chapitre());
        model.addAttribute("matiere", matiere);
        return "add-chapitre";
    }

    @PostMapping("/{matiereId}/chapitres/add")
    public String addChapitre(@PathVariable Long matiereId, @ModelAttribute("chapitre") Chapitre chapitre) {
        Matiere matiere = matiereRepository.findById(matiereId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Matiere Id:" + matiereId));
        chapitre.setMatiere(matiere);
        chapitreRepository.save(chapitre);
        return "redirect:/matieres/" + matiereId + "/chapitres/add";
    }

    @GetMapping("/{matiereId}/chapitres/{chapitreId}/cours/add")
    public String showAddCourForm(@PathVariable Long matiereId, @PathVariable Long chapitreId, Model model) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Chapitre Id:" + chapitreId));
        model.addAttribute("cour", new Cour());
        model.addAttribute("chapitre", chapitre);
        return "add-cour";
    }

    @PostMapping("/{matiereId}/chapitres/{chapitreId}/cours/add")
    public String addCour(@PathVariable Long matiereId, @PathVariable Long chapitreId, @ModelAttribute("cour") Cour cour) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Chapitre Id:" + chapitreId));
        cour.setChapitre(chapitre);
        courRepository.save(cour);
        return "redirect:/matieres/" + matiereId + "/chapitres/" + chapitreId + "/cours/add";
    }

    // Add similar methods for updating and deleting Chapitres and Cours
}

