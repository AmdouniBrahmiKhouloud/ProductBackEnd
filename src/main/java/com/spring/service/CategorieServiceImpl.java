package com.spring.service;

import com.spring.entity.Categorie;
import com.spring.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategorieServiceImpl implements ICategorieService{
    @Autowired
    private CategorieRepository categorieRepository;
    @Override
    public Categorie addCategorie(Categorie categorie) {
        categorie.setDateCreation(new Date());
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorie(Categorie categorie, Long id) {
        Categorie categorie1 = categorieRepository.findById(id).orElse(null);
        categorie1.setNom(categorie.getNom());
        categorie1.setQt(categorie.getQt());
        categorie1.setDateModif(new Date());
        return categorieRepository.save(categorie1);
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public List<Categorie> findAllCategorie() {
        return (List<Categorie>) categorieRepository.findAll();
    }

    @Override
    public Categorie findOneCategorie(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }
}
