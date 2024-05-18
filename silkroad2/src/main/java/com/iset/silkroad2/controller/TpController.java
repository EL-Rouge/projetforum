package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Chapitre;
import com.iset.silkroad2.entities.Tp;
import com.iset.silkroad2.repository.ChapitreRepository;
import com.iset.silkroad2.repository.TpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/tp")
public class TpController {

    @Autowired
    private ChapitreRepository chapitreRepository;

    @Autowired
    private TpRepository tpRepository;

    @PostMapping("/add")
    public String addTp(@RequestParam Long chapitreId, @RequestParam String tpTitle) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId).orElseThrow(() -> new RuntimeException("Chapitre not found"));
        Tp tp = new Tp();
        tp.setTitle(tpTitle);
        chapitre.addTp(tp);
        chapitreRepository.save(chapitre);
        return "redirect:/matieres/matieres"; // Redirect to the matieres page
    }
}
