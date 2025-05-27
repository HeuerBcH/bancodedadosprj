package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Gestor;
import com.projetobancodedados.projetobd.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ResponseEntity<?> createGestor(@RequestBody Gestor gestor) {
        if (gestor.getIdGestor() == null) {
            return ResponseEntity.badRequest().body("O campo idGestor é obrigatório.");
        }
        try {
            return ResponseEntity.ok(gestorRepository.save(gestor));
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().body("Erro: O idGestor informado não existe na tabela Funcionario. Crie primeiro o Funcionario correspondente.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gestor> updateGestor(@PathVariable Integer id, @RequestBody Gestor gestorDetails) {
        return gestorRepository.findById(id).map(gestor -> {
            gestor.setGrupoGerido(gestorDetails.getGrupoGerido());
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
