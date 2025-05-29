package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.FuncionarioEscala;
import com.projetobancodedados.projetobd.model.Funcionario;
import com.projetobancodedados.projetobd.model.EscalaDeTrabalho;
import com.projetobancodedados.projetobd.repository.FuncionarioEscalaRepository;
import com.projetobancodedados.projetobd.repository.FuncionarioRepository;
import com.projetobancodedados.projetobd.repository.EscalaDeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario_escala")
public class FuncionarioEscalaController {

    @Autowired
    private FuncionarioEscalaRepository funcionarioEscalaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EscalaDeTrabalhoRepository escalaDeTrabalhoRepository;

    @GetMapping
    public List<FuncionarioEscala> getAll() {
        return funcionarioEscalaRepository.findAll();
    }

    @GetMapping("/{funcionarioId}/{escalaDeTrabalhoId}")
    public ResponseEntity<FuncionarioEscala> getById(
            @PathVariable Integer funcionarioId,
            @PathVariable Integer escalaDeTrabalhoId) {
        FuncionarioEscala.FuncionarioEscalaId id = new FuncionarioEscala.FuncionarioEscalaId(funcionarioId, escalaDeTrabalhoId);
        return funcionarioEscalaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FuncionarioEscala funcionarioEscala) {
        // Busca as entidades pelo ID
        Integer funcionarioId = funcionarioEscala.getId().getFuncionarioId();
        Integer escalaId = funcionarioEscala.getId().getEscalaDeTrabalhoId();

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElse(null);
        EscalaDeTrabalho escala = escalaDeTrabalhoRepository.findById(escalaId).orElse(null);

        if (funcionario == null || escala == null) {
            return ResponseEntity.badRequest().body("Funcionario ou EscalaDeTrabalho não encontrados.");
        }

        funcionarioEscala.setFuncionario(funcionario);
        funcionarioEscala.setEscalaDeTrabalho(escala);

        FuncionarioEscala saved = funcionarioEscalaRepository.save(funcionarioEscala);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{funcionarioId}/{escalaDeTrabalhoId}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer funcionarioId,
            @PathVariable Integer escalaDeTrabalhoId) {
        FuncionarioEscala.FuncionarioEscalaId id = new FuncionarioEscala.FuncionarioEscalaId(funcionarioId, escalaDeTrabalhoId);
        return funcionarioEscalaRepository.findById(id).map(fEscala -> {
            funcionarioEscalaRepository.delete(fEscala);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
