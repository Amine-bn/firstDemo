package com.iset.bibliotheque.Services;

import com.iset.bibliotheque.Dto.SignupRequestDTO;
import com.iset.bibliotheque.Dto.UserDto;
import com.iset.bibliotheque.Entity.User;
import com.iset.bibliotheque.Repository.UserRepository;
import com.iset.bibliotheque.Services.AuthService.AuthService;
import com.iset.bibliotheque.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceIm implements AuthService {
    @Autowired //pour l'injection d'un service dans un autre envirenement
    private UserRepository userRepository;
    public UserDto signupClient (SignupRequestDTO signupRequestDTO){
        User user =new User();
        user.setNom(signupRequestDTO.getNom());
        user.setPrenom(signupRequestDTO.getPrenom());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(signupRequestDTO.getPassword());
        user.setTelephone(signupRequestDTO.getTelephone());
        user.setRole(UserRole.Client);
        return userRepository.save(user).getUserDto();//convert user untity to dto
    }
    public Boolean presentByEmail(String email){
        return userRepository.findByEmail(email)!=null;
    }
}

