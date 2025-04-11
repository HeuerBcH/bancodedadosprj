package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Users;
import com.projetobancodedados.projetobd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Define que essa classe é um controlador REST
@RestController
//Define a URL base para todos os endpoints desse controlador
@RequestMapping("/users")

public class UsersController {

    // Injeta automaticamente o repositório no controlador
    @Autowired
    private UsersRepository usersRepository;
    
    // Retorna a lista de todos os usuários
    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
    
    // Retorna um usuário específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        return usersRepository.findById(id)
                .map(ResponseEntity::ok) // Se encontrado, retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrado, retorna 404 Not Found
    }
    
    // Cria um novo usuário
    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersRepository.save(user);
    }
    
    // Atualiza os dados de um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody Users updatedUser) {
        return usersRepository.findById(id).map(user -> {
            // Atualiza os campos
            user.setEmail(updatedUser.getEmail());
            user.setSenha(updatedUser.getSenha());
            user.setNivel(updatedUser.isNivel());
            user.setUsername(updatedUser.getUsername());
            user.setSetor(updatedUser.getSetor());
            user.setCcpadrao(updatedUser.getCcpadrao());
            // Salva no banco de dados
            usersRepository.save(user);
            return ResponseEntity.ok(user); // Retorna 200 OK com o usuário atualizado
        }).orElse(ResponseEntity.notFound().build()); // Se não encontrar o usuário, retorna 404
    }
    
    // Deleta um usuário pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        return usersRepository.findById(id)
            .map(user -> {
                usersRepository.delete(user);
                return ResponseEntity.noContent().<Void>build(); // 👈 ESSE PONTO É IMPORTANTE
            })
            .orElse(ResponseEntity.notFound().build());
    }
	
}
