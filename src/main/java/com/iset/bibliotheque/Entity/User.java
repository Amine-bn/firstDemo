package com.iset.bibliotheque.Entity;

import com.iset.bibliotheque.Dto.UserDto;
import com.iset.bibliotheque.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Users")
@Data //genere les getters et les setters
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nom;
    private String prenom;
    private String password;
    private String telephone;
    private UserRole role;

    public UserDto getUserDto() {//conveert tdo to untity
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setEmail(email);
        userDto.setNom(nom);
        userDto.setPrenom(prenom);
        userDto.setPassword(password);
        userDto.setTelephone(telephone);
        userDto.setRole(role);
        return userDto;
    }


}
