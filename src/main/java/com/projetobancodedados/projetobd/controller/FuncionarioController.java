package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Funcionario;
import com.projetobancodedados.projetobd.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository employeeRepository;

    @GetMapping
    public List<Funcionario> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Funcionario createEmployee(@RequestBody Funcionario employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateEmployee(@PathVariable Integer id, @RequestBody Funcionario updated) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updated.getName());
            employee.setUser(updated.getUsers());
            employee.setManager(updated.getManager());
            return ResponseEntity.ok(employeeRepository.save(employee));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        return employeeRepository.findById(id).map(employee -> {
            employeeRepository.delete(employee);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}