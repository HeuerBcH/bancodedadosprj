package com.projetobancodedados.projetobd.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;

@Controller
public class PageController {

    @GetMapping("/inicio.html")
    public ResponseEntity<byte[]> inicio() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("private-static/inicio.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(bytes);
    }

    @GetMapping("/atividade-crud.html")
    public ResponseEntity<byte[]> atividade() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("private-static/atividade-crud.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(bytes);
    }

    @GetMapping("/cliente-crud.html")
    public ResponseEntity<byte[]> cliente() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("private-static/cliente-crud.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(bytes);
    }

    @GetMapping("/contrato-crud.html")
    public ResponseEntity<byte[]> contrato() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("private-static/contrato-crud.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(bytes);
    }

    @GetMapping("/escala-de-trabalho-crud.html")
    public ResponseEntity<byte[]> escala() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("private-static/escala-de-trabalho-crud.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(bytes);
    }

    @GetMapping("/feriado-crud.html")
    public ResponseEntity<byte[]> feriado() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("private-static/feriado-crud.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(bytes);
    }

    @GetMapping("/funcionario-crud.html")
    public ResponseEntity<byte[]> funcionario() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("private-static/funcionario-crud.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(bytes);
    }

    @GetMapping("/gestor-crud.html")
    public ResponseEntity<byte[]> gestor() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("private-static/gestor-crud.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(bytes);
    }

    @GetMapping("/users-crud.html")
    public ResponseEntity<byte[]> users() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("private-static/users-crud.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(bytes);
    }

    @GetMapping("/apontamento-crud.html")
    public ResponseEntity<byte[]> apontamento() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("private-static/apontamento-crud.html");
        byte[] bytes = Files.readAllBytes(htmlFile.getFile().toPath());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(bytes);
    }
}
