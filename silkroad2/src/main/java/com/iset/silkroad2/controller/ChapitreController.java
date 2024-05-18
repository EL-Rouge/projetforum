package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Chapitre;
import com.iset.silkroad2.entities.Matiere;
import com.iset.silkroad2.repository.ChapitreRepository;
import com.iset.silkroad2.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chapitres")
public class ChapitreController {

    @Autowired
    private ChapitreRepository chapitreRepository;
    @Autowired
    private MatiereRepository matiereRepository;

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

    @PostMapping("/addChapter")
    public String addChapter(@RequestParam Long matiereId, @RequestParam String chapterTitle) {
        Matiere matiere = matiereRepository.findById(matiereId).orElseThrow(() -> new IllegalArgumentException("Invalid matiere Id:" + matiereId));
        Chapitre newChapter = new Chapitre();
        newChapter.setTitre(chapterTitle);
        newChapter.setMatiere(matiere);
        chapitreRepository.save(newChapter);
        return "redirect:/matieres/matieres";
    }

    @DeleteMapping("/delete/{chapitreId}")
    public String deleteChapter(@PathVariable Long chapitreId) {
        chapitreRepository.deleteById(chapitreId);

        return "redirect:/matieres/matieres";
    }

}
