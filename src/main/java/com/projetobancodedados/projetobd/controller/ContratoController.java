package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Contrato;
import com.projetobancodedados.projetobd.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

    @Autowired
    private ContratoRepository contractRepository;

    @GetMapping
    public List<Contrato> getAllContracts() {
        return contractRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> getContractById(@PathVariable Integer id) {
        return contractRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contrato createContract(@RequestBody Contrato contract) {
        return contractRepository.save(contract);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> updateContract(@PathVariable Integer id, @RequestBody Contrato updated) {
        return contractRepository.findById(id).map(contract -> {
            contract.setActivityDescription(updated.getActivityDescription());
            contract.setStartDate(updated.getStartDate());
            contract.setEndDate(updated.getEndDate());
            contract.setStatus(updated.getStatus());
            contract.setClient(updated.getClient());
            contract.setEmployee(updated.getEmployee());
            contract.setManager(updated.getManager());
            return ResponseEntity.ok(contractRepository.save(contract));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Integer id) {
        return contractRepository.findById(id).map(contract -> {
            contractRepository.delete(contract);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}