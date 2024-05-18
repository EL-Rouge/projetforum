package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Chapitre;
import com.iset.silkroad2.entities.Cour;
import com.iset.silkroad2.entities.Td;
import com.iset.silkroad2.entities.Tp;
import com.iset.silkroad2.repository.ChapitreRepository;
import com.iset.silkroad2.repository.CourRepository;
import com.iset.silkroad2.repository.TdRepository;
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
@RequestMapping("/contents")
public class ContentController {

    @Autowired
    private ChapitreRepository chapitreRepository;

    @Autowired
    private CourRepository courRepository;

    @Autowired
    private TpRepository tpRepository;

    @Autowired
    private TdRepository tdRepository;

    @PostMapping("/addContent")
    public String addContent(@RequestParam("matiereId") Long matiereId,
                             @RequestParam("contentType") String contentType,
                             @RequestParam("contentTitle") String contentTitle,
                             @RequestParam("file") MultipartFile file) {
        System.out.println("Adding content: " + contentType);
        System.out.println("Matiere ID: " + matiereId);
        System.out.println("Content Title: " + contentTitle);

        // Find the chapitre by matiereId
        Optional<Chapitre> chapitreOpt = chapitreRepository.findById(matiereId);
        if (chapitreOpt.isPresent()) {
            Chapitre chapitre = chapitreOpt.get();

            String filePath = saveFile(file);
            if (filePath == null) {
                System.out.println("Failed to save file");
                return "redirect:/matieres";
            }

            if ("cour".equals(contentType)) {
                Cour cour = new Cour();
                cour.setTitle(contentTitle);
                cour.setFilePath(filePath);
                cour.setChapitre(chapitre);
                courRepository.save(cour);
                System.out.println("Cour saved: " + cour.getId());
            } else if ("tp".equals(contentType)) {
                Tp tp = new Tp();
                tp.setTitle(contentTitle);
                tp.setFilePath(filePath);
                tp.setChapitre(chapitre);
                tpRepository.save(tp);
                System.out.println("TP saved: " + tp.getId());
            } else if ("td".equals(contentType)) {
                Td td = new Td();
                td.setTitle(contentTitle);
                td.setFilePath(filePath);
                td.setChapitre(chapitre);
                tdRepository.save(td);
                System.out.println("TD saved: " + td.getId());
            }
        } else {
            System.out.println("Chapitre not found for Matiere ID: " + matiereId);
        }
        return "redirect:/matieres/matieres";
    }

    private String saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            String filePath = "/path/to/uploaded/files/" + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);
            System.out.println("File saved: " + filePath);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


