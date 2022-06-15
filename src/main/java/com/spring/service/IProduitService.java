package com.spring.service;


import com.spring.entity.Categorie;
import com.spring.entity.Produit;

import java.util.List;

public interface IProduitService {
    Produit addProduit(Long categorieId , Produit produit) ;
    Produit updateProduit (Produit produit, Long produitId, Long categorieId);
    void deleteProduit (Long id);
    List<Produit> findAllProduit();
    Produit findOneProduit(Long id);
}
