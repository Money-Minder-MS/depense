package com.moneyminder.depense.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "depenses")
@Data                  // génère getters, setters, toString
@NoArgsConstructor     // constructeur vide
@AllArgsConstructor    // constructeur avec tous les champs
@Builder               // pattern builder
public class Depense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    private String description;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private BigDecimal montant;

    @NotBlank(message = "La catégorie est obligatoire")
    private String categorie;  // Ex: alimentation, transport, loisir...

    @NotNull(message = "La date est obligatoire")
    private LocalDate date;

    private Long userId;  // ID de l'utilisateur (depuis un autre microservice)
}