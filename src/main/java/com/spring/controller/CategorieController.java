package com.spring.controller;

import com.spring.entity.Categorie;
import com.spring.service.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Categorie")
public class CategorieController {
    @Autowired
    private ICategorieService categorieService;
    @GetMapping("/retrieve-categorie")
    @PreAuthorize("hasRole('ProductRealm-admin')")
    /**@RolesAllowed("ProductRealm-admin")**/
    public List<Categorie> retrieveAllCategories() {
        return categorieService.findAllCategorie();
    }


    @GetMapping("/retrieve-categorie/{categorie-id}")
    @ResponseBody
    public ResponseEntity<Categorie> retrieveCategorie(@PathVariable("categorie-id") Long categorieId) {
        Categorie foundCategorie=categorieService.findOneCategorie(categorieId);
        if (foundCategorie == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundCategorie);
        }
    }

    @PostMapping("/add-categorie")
    public ResponseEntity<Categorie> createCategorie (@RequestBody Categorie categorie) throws URISyntaxException {
        Categorie createdCategorie= categorieService.addCategorie(categorie);
        if (createdCategorie == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/retrieve-categorie/{categorie-id}")
                    .buildAndExpand(createdCategorie.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdCategorie);
        }
    }

    @DeleteMapping("/remove-categorie/{categorie-id}")
    @ResponseBody
    public ResponseEntity<Object> removeCategorie(@PathVariable("categorie-id") Long categorieId) {
        categorieService.deleteCategorie(categorieId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/modify-categorie/{categorie-id}")
    @ResponseBody
    public ResponseEntity<Categorie> modifyCategorie(@RequestBody Categorie categorie, @PathVariable("categorie-id") Long categorieId) {
        Categorie updatedCategorie =categorieService.updateCategorie(categorie,categorieId);
        if (updatedCategorie == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedCategorie);
        }
    }
}
