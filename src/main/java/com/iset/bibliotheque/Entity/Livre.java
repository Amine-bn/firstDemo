package com.iset.bibliotheque.Entity;

import com.iset.bibliotheque.Dto.LivreDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data //notation de lombok genere les getters , les setters et tostring
@NoArgsConstructor //constructeur sans parametres
@AllArgsConstructor
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private String auteur;
    private String Date;
    //ajouter livre theme 

    @Column(name="nameFile")
    private String nameFile;
    @Column(name="typeFile")
    private String typeFile;
    @Column(name="contenuFile",length = 50000000)
    private byte[] contenuFile; //le contunu de livre pdf ou ...

    @Column(name="nameCover")
    private String nameCover;
    @Column(name="typeCover")
    private String typeCover;
    @Column(name="contenuCover",length = 50000000)
    private byte[] contenuCover;

    public LivreDto getLivreDto() {
        LivreDto livreDto = new LivreDto();
        livreDto.setDescription(description);
        livreDto.setId(id);
        livreDto.setTitre(titre);
        livreDto.setAuteur(auteur);
        livreDto.setDate(Date);
        return livreDto;
    }
}