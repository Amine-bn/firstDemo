package com.iset.bibliotheque.Dto;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private Long id;
    private String email;
    private String nom;
    private String prenom;
    private String password;
    private String telephone;
}
