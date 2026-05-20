package com.moneyminder.depense.client;

import com.moneyminder.depense.dto.UtilisateurDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
@RequiredArgsConstructor
public class UtilisateurClient {

    private final WebClient.Builder webClientBuilder;

    // Appelle GET /api/utilisateurs/getbyid/{id} sur GESTION-UTILISATEUR
    public UtilisateurDTO getUtilisateurById(Long userId) {
        try {
            return webClientBuilder
                    .build()
                    .get()
                    .uri("lb://gestion-utilisateur/api/utilisateurs/getbyid/" + userId)
                    .retrieve()
                    .bodyToMono(UtilisateurDTO.class)
                    .block();  // block() = attendre la réponse (appel synchrone)
        } catch (WebClientResponseException.NotFound e) {
            return null;  // utilisateur non trouvé
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la communication avec gestion-utilisateur : " + e.getMessage());
        }
    }
}