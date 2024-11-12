package com.iset.bibliotheque.Services.AuthService;

import com.iset.bibliotheque.Dto.LivreDto;
import com.iset.bibliotheque.Entity.Livre;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LivreService {
    void createLivre(String livreDto, MultipartFile livrePdf,MultipartFile livreCover) throws IOException;
    boolean existeLivreAvecTitreEtAuteur(String livreDtoString) throws IOException;
    LivreDto updateLivre(Long id,LivreDto livreDto);
    List<Livre> getAllLivre() throws IOException;
    void deleteLivre(Long id) ;

}
