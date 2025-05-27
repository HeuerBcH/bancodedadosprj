package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Atividade;
import com.projetobancodedados.projetobd.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

    @Autowired
    private AtividadeRepository activityRepository;

    @GetMapping
    public List<Atividade> getAllActivities() {
        return activityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> getActivityById(@PathVariable Integer id) {
        return activityRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Atividade createActivity(@RequestBody Atividade activity) {
        return activityRepository.save(activity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> updateActivity(@PathVariable Integer id, @RequestBody Atividade updated) {
        return activityRepository.findById(id).map(activity -> {
            activity.setDescription(updated.getDescription());
            activity.setInternal(updated.getInternal());
            return ResponseEntity.ok(activityRepository.save(activity));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Integer id) {
        return activityRepository.findById(id).map(activity -> {
            activityRepository.delete(activity);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
