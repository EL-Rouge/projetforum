package com.iset.silkroad2.controller;

import com.iset.silkroad2.entities.Chapitre;
import com.iset.silkroad2.entities.Cour;
import com.iset.silkroad2.repository.ChapitreRepository;
import com.iset.silkroad2.repository.CourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/cours")
public class CourController {

    @Autowired
    private CourRepository courRepository;

    @Autowired
    private Environment env;


    @Autowired
    private ChapitreRepository chapitreRepository;




    @GetMapping("/add")
    public String showAddCourForm(Model model) {
        model.addAttribute("cour", new Cour());
        return "add-cour";
    }

//    , @RequestParam("file") MultipartFile file

    @PostMapping("/add")
    public String addCour(@RequestParam("chapitreId") Long chapitreId,
                          @RequestParam("courTitle") String courTitle,
                          @RequestParam("file") MultipartFile file,
                          Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "redirect:/somePage"; // Change this to the appropriate page
        }

        try {
            // Directory where files will be stored
            Path directoryPath = Paths.get("src/main/resources/static/uploads");
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // Generate a unique identifier for the file
            String uniqueId = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            String newFilename = uniqueId + "_" + originalFilename;

            // Get the file and save it somewhere with a unique name
            Path filePath = directoryPath.resolve(newFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Find the Chapitre entity by chapitreId
            Chapitre chapitre = chapitreRepository.findById(chapitreId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid chapitre Id: " + chapitreId));

            // Save the Cour entity with the file path and associated Chapitre
            Cour cour = new Cour();
            cour.setTitle(courTitle);
            cour.setFilePath("/uploads/" + newFilename);
            cour.setChapitre(chapitre);
            courRepository.save(cour);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/matieres/matieres"; // Change this to the appropriate page
    }


    @DeleteMapping("/delete/{courid}")
    public String deleteChapter(@PathVariable Long courid) {
        courRepository.deleteById(courid);

        return "redirect:/matieres/matieres";
    }


}

