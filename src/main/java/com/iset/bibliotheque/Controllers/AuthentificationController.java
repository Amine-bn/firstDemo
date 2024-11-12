package com.iset.bibliotheque.Controllers;


import com.iset.bibliotheque.Dto.SignupRequestDTO;
import com.iset.bibliotheque.Dto.UserDto;
import com.iset.bibliotheque.Services.AuthService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentification")
public class AuthentificationController {
    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<?> signUpClient(@RequestBody SignupRequestDTO signupRequestDTO) {

        if(authService.presentByEmail(signupRequestDTO.getEmail())) {
            return  new ResponseEntity<>("Déja existe un compte avec ce email",HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser =authService.signupClient(signupRequestDTO);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

}
