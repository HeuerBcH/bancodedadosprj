package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.EscalaDeTrabalho;
import com.projetobancodedados.projetobd.repository.EscalaDeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/escala-de-trabalho")
public class EscalaDeTrabalhoController {
    @Autowired
    private EscalaDeTrabalhoRepository escalaDeTrabalhoRepository;

    @GetMapping
    public List<EscalaDeTrabalho> getAllEscalas() {
        return escalaDeTrabalhoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EscalaDeTrabalho> getEscalaById(@PathVariable Integer id) {
        return escalaDeTrabalhoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public EscalaDeTrabalho createEscala(@RequestBody EscalaDeTrabalho escalaDeTrabalho) {
        return escalaDeTrabalhoRepository.save(escalaDeTrabalho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscalaDeTrabalho> updateEscala(@PathVariable Integer id, @RequestBody EscalaDeTrabalho updated) {
        return escalaDeTrabalhoRepository.findById(id).map(escala -> {
            escala.setHoras_semanais(updated.getHoras_semanais());
            escala.setDias_semana(updated.getDias_semana());
            return ResponseEntity.ok(escalaDeTrabalhoRepository.save(escala));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEscala(@PathVariable Integer id) {
        return escalaDeTrabalhoRepository.findById(id).map(escala -> {
            escalaDeTrabalhoRepository.delete(escala);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // Escalas por horas semanais
    @GetMapping("/count-by-horas")
    public Map<Integer, Long> countByHoras() {
        return escalaDeTrabalhoRepository.findAll().stream()
            .collect(Collectors.groupingBy(EscalaDeTrabalho::getHoras_semanais, Collectors.counting()));
    }

    // Escalas por dias da semana (string)
    @GetMapping("/count-by-dias")
    public Map<String, Long> countByDias() {
        return escalaDeTrabalhoRepository.findAll().stream()
            .collect(Collectors.groupingBy(EscalaDeTrabalho::getDias_semana, Collectors.counting()));
    }

    // Escalas por tipo de jornada (ex: 5x2, 6x1, etc) - se houver padrão
    // Se não houver, pode omitir este gráfico
}
