package com.moneyminder.depense.controller;

import com.moneyminder.depense.dto.DepenseDTO;
import com.moneyminder.depense.service.DepenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/depenses")
@RequiredArgsConstructor
public class DepenseController {

    private final DepenseService service;

    // POST /api/depenses → Créer
    @PostMapping
    public ResponseEntity<DepenseDTO> creer(@Valid @RequestBody DepenseDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.creer(dto));
    }

    // GET /api/depenses → Lister tout
    @GetMapping
    public ResponseEntity<List<DepenseDTO>> listerTout() {
        return ResponseEntity.ok(service.listerTout());
    }

    // GET /api/depenses/{id} → Trouver par ID
    @GetMapping("/{id}")
    public ResponseEntity<DepenseDTO> trouverParId(@PathVariable Long id) {
        return ResponseEntity.ok(service.trouverParId(id));
    }

    // PUT /api/depenses/{id} → Modifier
    @PutMapping("/{id}")
    public ResponseEntity<DepenseDTO> modifier(@PathVariable Long id,
                                               @Valid @RequestBody DepenseDTO dto) {
        return ResponseEntity.ok(service.modifier(id, dto));
    }

    // DELETE /api/depenses/{id} → Supprimer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        service.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}