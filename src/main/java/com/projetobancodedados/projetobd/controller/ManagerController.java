package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Manager;
import com.projetobancodedados.projetobd.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Integer id) {
        return managerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Manager createManager(@RequestBody Manager manager) {
        return managerRepository.save(manager);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@PathVariable Integer id, @RequestBody Manager updated) {
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
