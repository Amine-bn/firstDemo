package com.iset.bibliotheque.Dto;

import lombok.Data;

@Data
public class LivreDto {
    private Long id;
    private String titre;
    private String description;
    private String auteur  ;
    private String Date;
    private byte[] contenuCover;
    private String nameCover;
    private String nameFile;
    private byte[] contenuFile; //le contunu de livre pdf ou ...


}
