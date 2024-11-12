package com.iset.bibliotheque.Repository;

import com.iset.bibliotheque.Entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre,Long> {
    Livre findByTitreAndAuteur(String titre, String auteur);

    Livre findByTitre(String titre); //    Livre updateLivre(Livre livre);

    boolean existsByTitreAndAuteur(String titre, String auteur);

    @Override
    void deleteById(Long id);
}
