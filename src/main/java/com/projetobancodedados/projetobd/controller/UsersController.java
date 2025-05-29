package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Funcionario;
import com.projetobancodedados.projetobd.model.Users;
import com.projetobancodedados.projetobd.repository.FuncionarioRepository;
import com.projetobancodedados.projetobd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Define que essa classe é um controlador REST
@RestController
//Define a URL base para todos os endpoints desse controlador
@RequestMapping("/users")
public class UsersController {

    // Injeta automaticamente o repositório no controlador
    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
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
    public ResponseEntity<Users> createUser(@RequestBody Users usuario) {
        Users novoUsuario = usersRepository.save(usuario);

        // Cria e salva o funcionário correspondente
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(novoUsuario.getUsername());
        funcionario.setFkUsersIdUser(novoUsuario.getId_user());
        funcionarioRepository.save(funcionario);

        return ResponseEntity.ok(novoUsuario);
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
	
	// Usuários por setor
    @GetMapping("/count-by-setor")
    public Map<String, Long> countBySetor() {
        return usersRepository.findAll().stream()
            .collect(Collectors.groupingBy(Users::getSetor, Collectors.counting()));
    }

    @GetMapping("/count-by-ccpadrao")
    public Map<Integer, Long> countByCcpadrao() {
        return usersRepository.findAll().stream()
            .collect(Collectors.groupingBy(Users::getCcpadrao, Collectors.counting()));
    }

    // Usuários por nível (admin/comum)
    @GetMapping("/count-by-nivel")
    public Map<String, Long> countByNivel() {
        return usersRepository.findAll().stream()
            .collect(Collectors.groupingBy(u -> u.isNivel() ? "Admin" : "Usuário", Collectors.counting()));
    }
}
