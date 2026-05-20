package com.moneyminder.depense.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepenseDTO {

    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    private String description;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal montant;

    @NotBlank(message = "La catégorie est obligatoire")
    private String categorie;

    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

    @NotNull(message = "L'userId est obligatoire")
    private Long userId;

    // ✅ Infos utilisateur (remplies automatiquement depuis gestion-utilisateur)
    private String utilisateurNom;
    private String utilisateurPrenom;
    private String utilisateurEmail;
}