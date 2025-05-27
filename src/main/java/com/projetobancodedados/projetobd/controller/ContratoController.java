package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Contrato;
import com.projetobancodedados.projetobd.repository.ClienteRepository;
import com.projetobancodedados.projetobd.repository.ContratoRepository;
import com.projetobancodedados.projetobd.repository.FuncionarioRepository;
import com.projetobancodedados.projetobd.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private GestorRepository gestorRepository;

    @GetMapping
    public List<Contrato> getAllContratos() {
        return contratoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> getContratoById(@PathVariable Integer id) {
        return contratoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createContrato(@RequestBody Contrato contrato) {
        if (contrato.getCliente() == null || contrato.getCliente().getId_cliente() == null) {
            return ResponseEntity.badRequest().body("ID do cliente é obrigatório.");
        }
        if (contrato.getFuncionario() == null || contrato.getFuncionario().getId_funcionario() == null) {
            return ResponseEntity.badRequest().body("ID do funcionário é obrigatório.");
        }
        if (contrato.getGestor() == null || contrato.getGestor().getIdGestor() == null) {
            return ResponseEntity.badRequest().body("ID do gestor é obrigatório.");
        }

        contrato.setCliente(clienteRepository.findById(contrato.getCliente().getId_cliente()).orElse(null));
        if (contrato.getCliente() == null) {
            return ResponseEntity.badRequest().body("Cliente não encontrado.");
        }

        contrato.setFuncionario(funcionarioRepository.findById(contrato.getFuncionario().getId_funcionario()).orElse(null));
        if (contrato.getFuncionario() == null) {
            return ResponseEntity.badRequest().body("Funcionário não encontrado.");
        }

        contrato.setGestor(gestorRepository.findById(contrato.getGestor().getIdGestor()).orElse(null));
        if (contrato.getGestor() == null) {
            return ResponseEntity.badRequest().body("Gestor não encontrado.");
        }

        return ResponseEntity.ok(contratoRepository.save(contrato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> updateContrato(@PathVariable Integer id, @RequestBody Contrato updated) {
        return contratoRepository.findById(id).map(contrato -> {
            contrato.setObj_contratado(updated.getObj_contratado());
            contrato.setData_inicio(updated.getData_inicio());
            contrato.setData_fim(updated.getData_fim());
            contrato.setEstado(updated.getEstado());
            contrato.setCliente(updated.getCliente());
            contrato.setFuncionario(updated.getFuncionario());
            contrato.setGestor(updated.getGestor());
            return ResponseEntity.ok(contratoRepository.save(contrato));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrato(@PathVariable Integer id) {
        return contratoRepository.findById(id).map(contrato -> {
            contratoRepository.delete(contrato);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}