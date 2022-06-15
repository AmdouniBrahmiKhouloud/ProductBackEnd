package com.spring.controller;

import com.spring.entity.Categorie;
import com.spring.service.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Categorie")
public class CategorieController {
    @Autowired
    private ICategorieService categorieService;

    @GetMapping("/retrieve-categorie")
    public List<Categorie> retrieveAllCategories() {
        return categorieService.findAllCategorie();
    }


    @GetMapping("/retrieve-categorie/{categorie-id}")
    @ResponseBody
    public Categorie retrieveCategorie(@PathVariable("categorie-id") Long categorieId) {
        return categorieService.findOneCategorie(categorieId);
    }

    @PostMapping("/add-categorie")
    public Categorie createCategorie( @RequestBody Categorie categorie) {
        return categorieService.addCategorie(categorie);
    }

    @DeleteMapping("/remove-categorie/{categorie-id}")
    @ResponseBody
    public void removeCategorie(@PathVariable("categorie-id") Long categorieId) {
        categorieService.deleteCategorie(categorieId);
    }
    @PutMapping("/modify-categorie/{categorie-id}")
    @ResponseBody
    public Categorie modifyCategorie(@RequestBody Categorie categorie, @PathVariable("categorie-id") Long categorieId) {
        return categorieService.updateCategorie(categorie,categorieId);
    }
}
