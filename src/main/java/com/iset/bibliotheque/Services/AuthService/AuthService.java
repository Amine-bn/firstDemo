package com.iset.bibliotheque.Services.AuthService;

import com.iset.bibliotheque.Dto.SignupRequestDTO;
import com.iset.bibliotheque.Dto.UserDto;

public interface AuthService {
    UserDto signupClient (SignupRequestDTO signupRequestDTO);
    Boolean presentByEmail(String email);
}
