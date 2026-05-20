package com.moneyminder.depense.repository;

import com.moneyminder.depense.model.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long> {

    // Trouver toutes les dépenses d'un utilisateur
    List<Depense> findByUserId(Long userId);

    // Trouver par catégorie
    List<Depense> findByCategorie(String categorie);
}