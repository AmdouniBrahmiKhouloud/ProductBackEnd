package com.spring.controller;



import com.spring.entity.Produit;
import com.spring.service.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Produit")
public class ProduitController {
    @Autowired
    private   IProduitService produitService;
    @GetMapping("/retrieve-produit")
    public List<Produit> retrieveAllProduits() {

        return produitService.findAllProduit();
    }

    @GetMapping("/retrieve-produit/{produit-id}")
    @ResponseBody
    public Produit retrieveProduit(@PathVariable("produit-id") Long produitId) {
        return produitService.findOneProduit(produitId);
    }

    @PostMapping("/add-produit/{categorie-id}")
    public Produit createProduit(@RequestBody Produit produit, @PathVariable("categorie-id") Long categorieId) {
        return produitService.addProduit(categorieId,produit);
    }

    @DeleteMapping("/remove-produit/{produit-id}")
    @ResponseBody
    public void removeProduit(@PathVariable("produit-id") Long produitId) {
        produitService.deleteProduit(produitId);
    }
    @PutMapping("/modify-produit/{produit-id}/{categorie-id}")
    @ResponseBody
    public Produit modifyProduit(@RequestBody Produit produit, @PathVariable("categorie-id") Long categorieId,@PathVariable("produit-id") Long produitId) {
        return produitService.updateProduit(produit,produitId,categorieId);
    }

}
