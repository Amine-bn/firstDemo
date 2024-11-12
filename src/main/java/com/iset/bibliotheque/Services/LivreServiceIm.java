package com.iset.bibliotheque.Services;


import com.iset.bibliotheque.Dto.LivreDto;
import com.iset.bibliotheque.Entity.Livre;
import com.iset.bibliotheque.Repository.LivreRepository;
import com.iset.bibliotheque.Repository.UserRepository;
import com.iset.bibliotheque.Services.AuthService.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.DataFormatException;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

@Service
public class LivreServiceIm implements LivreService {
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private UserRepository userRepository;
// ObjectMapper : conversion de dto au entity
    public void createLivre(String livreDto,MultipartFile livrePdf,MultipartFile livreCover) throws IOException {
        Livre Livre=new ObjectMapper().readValue(livreDto,Livre.class);//conversion de livre string a un livre objet

        Livre.setNameFile(livrePdf.getOriginalFilename());
        Livre.setTypeFile(livrePdf.getContentType());
        Livre.setContenuFile(compressBytes(livrePdf.getBytes()));
        Livre.setNameCover(livreCover.getOriginalFilename());
        Livre.setTypeCover(livreCover.getContentType());
        Livre.setContenuCover(compressBytes(livreCover.getBytes()));

        livreRepository.save(Livre);
    }
//    public boolean presentByTitre(String titre) {
//        return userRepository.findBy()
//

public boolean existeLivreAvecTitreEtAuteur(String livreDtoString) throws IOException{
    Livre livre1=new ObjectMapper().readValue(livreDtoString,Livre.class);
    return livreRepository.existsByTitreAndAuteur(livre1.getTitre(), livre1.getAuteur());
}
public LivreDto updateLivre(Long id, LivreDto livreDto) {

    Livre livreExsiste;
    livreExsiste = livreRepository.findById(id).orElse(null);
    if (livreExsiste!=null) {
        livreExsiste.setTitre(livreDto.getTitre());
        livreExsiste.setDescription(livreDto.getDescription());
        livreExsiste.setAuteur(livreDto.getAuteur());
        livreExsiste.setDate(livreDto.getDate());
        return livreRepository.save(livreExsiste).getLivreDto();

    }
    else return null;

}
    public List<Livre> getAllLivre() throws IOException {
        List<Livre> AllLivre= livreRepository.findAll();
        for (Livre livre : AllLivre) {
            if (livre.getContenuCover()!=null && livre.getContenuFile()!=null) {
                livre.setContenuFile(decompressBytes(livre.getContenuFile()));
                livre.setContenuCover(decompressBytes(livre.getContenuCover()));
            }
        }
    return AllLivre;
    }

    public void deleteLivre(Long id)  {
        livreRepository.deleteById(id);
    }
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

}
