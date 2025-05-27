package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Feriado;
import com.projetobancodedados.projetobd.repository.FeriadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feriado")
public class FeriadoController {

    @Autowired
    private FeriadoRepository feriadoRepository;

    @GetMapping
    public List<Feriado> getAllFeriados() {
        return feriadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feriado> getFeriadoById(@PathVariable Integer id) {
        return feriadoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createFeriado(@RequestBody Feriado feriado) {
        if (feriado.getIdFeriado() == null) {
            return ResponseEntity.badRequest().body("O campo idFeriado é obrigatório.");
        }
        return ResponseEntity.ok(feriadoRepository.save(feriado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feriado> updateFeriado(@PathVariable Integer id, @RequestBody Feriado feriadoDetails) {
        return feriadoRepository.findById(id).map(feriado -> {
            feriado.setNomeFeriado(feriadoDetails.getNomeFeriado());
            feriado.setDataFeriado(feriadoDetails.getDataFeriado());
            feriado.setPermiteLancamento(feriadoDetails.getPermiteLancamento());
            return ResponseEntity.ok(feriadoRepository.save(feriado));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeriado(@PathVariable Integer id) {
        return feriadoRepository.findById(id).map(feriado -> {
            feriadoRepository.delete(feriado);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
