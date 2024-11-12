package com.iset.bibliotheque.Dto;

import com.iset.bibliotheque.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String nom;
    private String prenom;
    private String password;
    private String telephone;
    private UserRole role;
}
