package com.iset.bibliotheque.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iset.bibliotheque.Dto.LivreDto;
import com.iset.bibliotheque.Entity.Livre;
import com.iset.bibliotheque.Repository.LivreRepository;
import com.iset.bibliotheque.Services.AuthService.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class LiverController {
    @Autowired
    private LivreService livreService;
    @Autowired
    private LivreRepository livreRepository;

    @GetMapping("/Livres")//aficher tous les livres
    public ResponseEntity<List<Livre>> findAll() throws IOException {
        List<Livre> AllLivres = livreRepository.findAll();
        return new ResponseEntity<>(AllLivres, HttpStatus.OK);

    }
    @PostMapping("/Livres/{id}") //supprmier un livre
    public ResponseEntity<?> deleteLivre(@PathVariable Long id) {
        if (livreRepository.existsById(id)) {
            livreRepository.deleteById(id);
            return new ResponseEntity<>("livre supprimer ",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("ce livre n'est pas trouver ",HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/Livres")//ajouter un livre
    public ResponseEntity<?> ajouterLivre(@RequestParam("livreDto")String livreDto,
                                          @RequestParam("livrePdf") MultipartFile livrePdf,
                                          @RequestParam("livreCover") MultipartFile livreCover) throws IOException {

        if (!livreService.existeLivreAvecTitreEtAuteur(livreDto)) {
            livreService.createLivre(livreDto,livrePdf,livreCover);
            return new ResponseEntity<>( HttpStatus.CREATED);
        }
         else{
            Livre Livre=new ObjectMapper().readValue(livreDto,Livre.class);
             return new ResponseEntity<>("Livre " +Livre.getTitre()+ " est deja existe ", HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PutMapping("/UpdateLivre/{id}")//modifier un livre
    public ResponseEntity<?> updateLivre(@PathVariable Long id, @RequestBody LivreDto livreDto) {
        if (livreService.updateLivre(id,livreDto)!=null) {
            return  new ResponseEntity<>("Le modification est bien fait ",HttpStatus.CREATED);
        }
        else{
            return  new ResponseEntity<>("le livre n'est pas trouver ",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
