package com.spring.service;

import com.spring.entity.Categorie;

import java.util.List;

public interface ICategorieService {
    Categorie addCategorie(Categorie categorie) ;
    Categorie updateCategorie (Categorie categorie, Long id);
    void deleteCategorie (Long id);
    List<Categorie> findAllCategorie();
    Categorie findOneCategorie(Long id);
}
