package com.spring.service;

import com.spring.entity.Categorie;
import com.spring.repository.CategorieRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ICategorieServiceTest {
    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieServiceImpl categorieService;
    @Test
    void addCategorie() {
        Categorie c = new Categorie();
        c.setNom("hh");
        c.setQt(20);
        c.setDateCreation(new Date());
        given(categorieRepository.save(c)).willAnswer(invocation -> invocation.getArgument(0));
        Categorie savedCategorie = categorieService.addCategorie(c);
        assertNotNull(savedCategorie);
        verify(categorieRepository).save(any(Categorie.class));
    }

    @Test
    void shouldReturnFindAll() {
        List<Categorie> data = new ArrayList();
        Categorie c = new Categorie();
        c.setId(3L);
        c.setNom("hh");
        c.setQt(20);
        c.setDateCreation(new Date(2022-06-13));
        data.add(c);
        given(categorieRepository.findAll()).willReturn(data);
        List<Categorie> expected = categorieService.findAllCategorie();
        assertNotNull(expected);
        assertEquals(expected,data);
        expected.forEach(categorie->log.info("categorie: "+categorie));

    }
    @Test
    void findOneCategorie() {
        Categorie c = new Categorie();
        c.setId(1L);
        c.setNom("hh");
        c.setQt(20);
        when(categorieRepository.findById(c.getId())).thenReturn(Optional.of(c));
        Categorie categorie = categorieService.findOneCategorie(c.getId());
        assertNotNull(categorie);
    }
    @Test
    void shouldDeleteCategorie() {
        Categorie c = new Categorie();
        c.setId(1L);
        c.setNom("hh");
        c.setQt(20);
        when(categorieRepository.findById(c.getId())).thenReturn(Optional.of(c));
        Categorie categorie = categorieService.findOneCategorie(c.getId());
        assertNotNull(categorie);
        categorieService.deleteCategorie(c.getId());
        verify(categorieRepository).deleteById(c.getId());
    }
}