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
    private GestorRepository managerRepository;

    @GetMapping
    public List<Gestor> getAllManagers() {
        return managerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gestor> getManagerById(@PathVariable Integer id) {
        return managerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gestor createManager(@RequestBody Gestor manager) {
        return managerRepository.save(manager);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gestor> updateManager(@PathVariable Integer id, @RequestBody Gestor updated) {
        return managerRepository.findById(id).map(manager -> {
            manager.setManagerGroup(updated.getManagerGroup());
            return ResponseEntity.ok(managerRepository.save(manager));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Integer id) {
        return managerRepository.findById(id).map(manager -> {
            managerRepository.delete(manager);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
