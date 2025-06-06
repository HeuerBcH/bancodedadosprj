package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Users;
import com.projetobancodedados.projetobd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users loginRequest) {
        // Busca usuário pelo email
        Optional<Users> userOpt = usersRepository.findAll().stream()
            .filter(u -> u.getEmail().equals(loginRequest.getEmail()) && u.getSenha().equals(loginRequest.getSenha()))
            .findFirst();

        if (userOpt.isPresent()) {
            // Retorne apenas o necessário, nunca a senha!
            Users user = userOpt.get();
            return ResponseEntity.ok().body("{\"username\":\"" + user.getUsername() + "\"}");
        } else {
            return ResponseEntity.status(401).body("Email ou senha inválidos.");
        }
    }

    @GetMapping("/api/auth/check")
    public ResponseEntity<?> checkAuth(HttpSession session) {
        // Supondo que você armazene o usuário autenticado na sessão
        if (session.getAttribute("usuario") != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}

// Redirecionamento da raiz "/" para login.html
@Controller
class RootRedirectController {
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login.html";
    }
}
