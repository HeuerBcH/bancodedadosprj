package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Apontamento;
import com.projetobancodedados.projetobd.repository.ApontamentoRepository;
import com.projetobancodedados.projetobd.repository.AtividadeRepository;
import com.projetobancodedados.projetobd.repository.FuncionarioRepository;
import com.projetobancodedados.projetobd.repository.FeriadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apontamento")
public class ApontamentoController {

    @Autowired
    private ApontamentoRepository apontamentoRepository;
    @Autowired
    private AtividadeRepository atividadeRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private FeriadoRepository feriadoRepository;

    @GetMapping
    public List<Apontamento> getAllApontamentos() {
        return apontamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apontamento> getApontamentoById(@PathVariable Integer id) {
        return apontamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createApontamento(@RequestBody Apontamento apontamento) {
        System.out.println("Recebido apontamento: " + apontamento);
        System.out.println("Atividade: " + (apontamento.getAtividade() != null ? apontamento.getAtividade().getId_atividade() : "null"));
        System.out.println("Funcionario: " + (apontamento.getFuncionario() != null ? apontamento.getFuncionario().getId_funcionario() : "null"));
        System.out.println("Feriado: " + (apontamento.getFeriado() != null ? apontamento.getFeriado().getId_feriado() : "null"));

        if (apontamento.getAtividade() == null || apontamento.getAtividade().getId_atividade() == null) {
            System.out.println("ID da atividade é obrigatório.");
            return ResponseEntity.badRequest().body("ID da atividade é obrigatório.");
        }
        if (apontamento.getFuncionario() == null || apontamento.getFuncionario().getId_funcionario() == null) {
            System.out.println("ID do funcionário é obrigatório.");
            return ResponseEntity.badRequest().body("ID do funcionário é obrigatório.");
        }

        apontamento.setAtividade(atividadeRepository.findById(apontamento.getAtividade().getId_atividade()).orElse(null));
        if (apontamento.getAtividade() == null) {
            System.out.println("Atividade não encontrada.");
            return ResponseEntity.badRequest().body("Atividade não encontrada.");
        }

        apontamento.setFuncionario(funcionarioRepository.findById(apontamento.getFuncionario().getId_funcionario()).orElse(null));
        if (apontamento.getFuncionario() == null) {
            System.out.println("Funcionário não encontrado.");
            return ResponseEntity.badRequest().body("Funcionário não encontrado.");
        }

        if (apontamento.getFeriado() != null && apontamento.getFeriado().getId_feriado() != null) {
            apontamento.setFeriado(feriadoRepository.findById(apontamento.getFeriado().getId_feriado()).orElse(null));
            if (apontamento.getFeriado() == null) {
                System.out.println("Feriado não encontrado.");
                return ResponseEntity.badRequest().body("Feriado não encontrado.");
            }
        } else {
            apontamento.setFeriado(null);
        }

        Apontamento salvo = apontamentoRepository.save(apontamento);
        System.out.println("Apontamento salvo: " + salvo);

        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apontamento> updateApontamento(@PathVariable Integer id, @RequestBody Apontamento updated) {
        return apontamentoRepository.findById(id).map(apontamento -> {
            apontamento.setHora_inicio(updated.getHora_inicio());
            apontamento.setHora_fim(updated.getHora_fim());
            apontamento.setCentro_de_custo(updated.getCentro_de_custo());
            apontamento.setData_apontamento(updated.getData_apontamento());
            apontamento.setData_preenchimento(updated.getData_preenchimento());
            apontamento.setAprovado(updated.getAprovado());
            apontamento.setAtividade(updated.getAtividade());
            apontamento.setFuncionario(updated.getFuncionario());
            apontamento.setFeriado(updated.getFeriado());
            return ResponseEntity.ok(apontamentoRepository.save(apontamento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApontamento(@PathVariable Integer id) {
        return apontamentoRepository.findById(id).map(apontamento -> {
            apontamentoRepository.delete(apontamento);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}