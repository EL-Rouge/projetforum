package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Chapitre;
import com.iset.silkroad2.entities.Td;
import com.iset.silkroad2.repository.ChapitreRepository;
import com.iset.silkroad2.repository.TdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/td")
public class TdController {

    @Autowired
    private ChapitreRepository chapitreRepository;

    @Autowired
    private TdRepository tdRepository;

    @PostMapping("/add")
    public String addTd(@RequestParam Long chapitreId, @RequestParam String tdTitle) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId).orElseThrow(() -> new RuntimeException("Chapitre not found"));
        Td td = new Td();
        td.setTitle(tdTitle);
        chapitre.addTd(td);
        chapitreRepository.save(chapitre);
        return "redirect:/matieres/matieres"; // Redirect to the matieres page
    }
}