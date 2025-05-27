package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Gestor;
import com.projetobancodedados.projetobd.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private GestorRepository gestorRepository;

    @GetMapping
    public List<Gestor> getAllGestores() {
        return gestorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gestor> getGestorById(@PathVariable Integer id) {
        return gestorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gestor createGestor(@RequestBody Gestor gestor) {
        return gestorRepository.save(gestor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gestor> updateGestor(@PathVariable Integer id, @RequestBody Gestor updated) {
        return gestorRepository.findById(id).map(gestor -> {
            gestor.setGrupoGerido(updated.getGrupoGerido());
            return ResponseEntity.ok(gestorRepository.save(gestor));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGestor(@PathVariable Integer id) {
        return gestorRepository.findById(id).map(gestor -> {
            gestorRepository.delete(gestor);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
