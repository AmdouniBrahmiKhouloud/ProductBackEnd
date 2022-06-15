package com.spring.service;

import com.spring.entity.Categorie;
import com.spring.entity.Produit;
import com.spring.repository.CategorieRepository;
import com.spring.repository.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService{
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Override
    public Produit addProduit(Long categorieId, Produit produit) {
        Categorie c = categorieRepository.findById(categorieId).orElse(null);
        log.info(c.toString());
        if (c != null){
        produit.setCategorie(c);
        }
        produit.setDateCreation(new Date());
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Produit produit, Long produitId, Long categorieId) {
        Categorie c = categorieRepository.findById(categorieId).orElse(null);
        Produit p = produitRepository.findById(produitId).orElse(null);
        if (c != null){
            p.setCategorie(c);
        }
        p.setNom(produit.getNom());
        p.setQt(produit.getQt());
        p.setDateModif(new Date());
        p.setDisponible(produit.isDisponible());
        return produitRepository.save(p);
    }


    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public List<Produit> findAllProduit() {
        return produitRepository.findAll();
    }

    @Override
    public Produit findOneProduit(Long id) {
        return produitRepository.findById(id).orElse(null);
    }
}
