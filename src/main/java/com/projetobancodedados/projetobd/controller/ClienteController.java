package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Cliente;
import com.projetobancodedados.projetobd.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente updated) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome_cliente(updated.getNome_cliente());
            cliente.setRua(updated.getRua());
            cliente.setNumero(updated.getNumero());
            cliente.setBairro(updated.getBairro());
            cliente.setCidade(updated.getCidade());
            cliente.setEstado(updated.getEstado());
            cliente.setCep(updated.getCep());
            cliente.setFax(updated.getFax());
            cliente.setTelefone(updated.getTelefone());
            cliente.setEmail(updated.getEmail());
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        return clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
