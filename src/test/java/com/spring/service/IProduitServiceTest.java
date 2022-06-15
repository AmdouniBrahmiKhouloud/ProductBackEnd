package com.spring.service;


import com.spring.entity.Categorie;
import com.spring.entity.Produit;
import com.spring.repository.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Slf4j
class IProduitServiceTest {
    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;
    @Test
    void addProduit() {
        Categorie c = new Categorie();
        c.setNom("hh");
        c.setQt(20);
        c.setDateCreation(new Date());
        Produit p = new Produit();
        p.setId(1L);
        p.setDisponible(true);
        p.setCategorie(c);
        p.setNom("lait");
        given(produitRepository.save(p)).willAnswer(invocation -> invocation.getArgument(0));
        Produit saved = produitService.addProduit(c.getId(),p);
        assertNotNull(saved);
        verify(produitRepository).save(any(Produit.class));
    }
    @Test
    void findAllProduit() {
        List<Produit> data = new ArrayList();
        Categorie c = new Categorie();
        c.setId(3L);
        c.setNom("hh");
        c.setQt(20);
        c.setDateCreation(new Date(2022-06-13));
        Produit p = new Produit();
        p.setId(1L);
        p.setDisponible(true);
        p.setCategorie(c);
        p.setNom("lait");
        data.add(p);
        given(produitRepository.findAll()).willReturn(data);
        List<Produit> expected = produitService.findAllProduit();
        assertNotNull(expected);
        assertEquals(expected,data);
        //expected.forEach(produit->log.info("Produit: "+produit));
    }

}