package com.moneyminder.depense.service;

import com.moneyminder.depense.client.UtilisateurClient;
import com.moneyminder.depense.dto.DepenseDTO;
import com.moneyminder.depense.dto.UtilisateurDTO;
import com.moneyminder.depense.model.Depense;
import com.moneyminder.depense.repository.DepenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepenseService {

    private final DepenseRepository repository;
    private final UtilisateurClient utilisateurClient;  // ← nouveau

    // ✅ Créer une dépense
    public DepenseDTO creer(DepenseDTO dto) {

        // 1. Vérifier que l'utilisateur existe
        UtilisateurDTO utilisateur = utilisateurClient.getUtilisateurById(dto.getUserId());
        if (utilisateur == null) {
            throw new RuntimeException("Utilisateur introuvable avec l'id : " + dto.getUserId());
        }

        // 2. Sauvegarder la dépense
        Depense depense = toEntity(dto);
        Depense sauvegardee = repository.save(depense);

        // 3. Retourner avec les infos utilisateur
        return toDTO(sauvegardee, utilisateur);
    }

    // 📋 Lister toutes les dépenses
    public List<DepenseDTO> listerTout() {
        return repository.findAll()
                .stream()
                .map(depense -> {
                    // Récupérer les infos utilisateur pour chaque dépense
                    UtilisateurDTO utilisateur = utilisateurClient.getUtilisateurById(depense.getUserId());
                    return toDTO(depense, utilisateur);
                })
                .collect(Collectors.toList());
    }

    // 🔍 Trouver par ID
    public DepenseDTO trouverParId(Long id) {
        Depense depense = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dépense non trouvée avec l'id : " + id));

        UtilisateurDTO utilisateur = utilisateurClient.getUtilisateurById(depense.getUserId());
        return toDTO(depense, utilisateur);
    }

    // ✏️ Modifier une dépense
    public DepenseDTO modifier(Long id, DepenseDTO dto) {
        Depense existante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dépense non trouvée avec l'id : " + id));

        // Vérifier que le nouvel utilisateur existe
        UtilisateurDTO utilisateur = utilisateurClient.getUtilisateurById(dto.getUserId());
        if (utilisateur == null) {
            throw new RuntimeException("Utilisateur introuvable avec l'id : " + dto.getUserId());
        }

        existante.setTitre(dto.getTitre());
        existante.setDescription(dto.getDescription());
        existante.setMontant(dto.getMontant());
        existante.setCategorie(dto.getCategorie());
        existante.setDate(dto.getDate());
        existante.setUserId(dto.getUserId());

        return toDTO(repository.save(existante), utilisateur);
    }

    // 🗑️ Supprimer
    public void supprimer(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Dépense non trouvée avec l'id : " + id);
        }
        repository.deleteById(id);
    }

    // 🔄 Entity → DTO (avec utilisateur)
    private DepenseDTO toDTO(Depense d, UtilisateurDTO u) {
        DepenseDTO dto = DepenseDTO.builder()
                .id(d.getId())
                .titre(d.getTitre())
                .description(d.getDescription())
                .montant(d.getMontant())
                .categorie(d.getCategorie())
                .date(d.getDate())
                .userId(d.getUserId())
                .build();

        // Ajouter les infos utilisateur si disponibles
        if (u != null) {
            dto.setUtilisateurNom(u.getNom());
            dto.setUtilisateurPrenom(u.getPrenom());
            dto.setUtilisateurEmail(u.getEmail());
        }

        return dto;
    }

    // 🔄 DTO → Entity
    private Depense toEntity(DepenseDTO dto) {
        return Depense.builder()
                .titre(dto.getTitre())
                .description(dto.getDescription())
                .montant(dto.getMontant())
                .categorie(dto.getCategorie())
                .date(dto.getDate())
                .userId(dto.getUserId())
                .build();
    }
}