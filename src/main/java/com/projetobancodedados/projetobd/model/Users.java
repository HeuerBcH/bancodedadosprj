package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import lombok.Data;


// Anotação que marca a classe como uma entidade JPA (será mapeada para uma tabela do banco de dados)
@Entity
// Gera automaticamente métodos como getters, setters, toString, equals e hashCode
@Data


public class Users {

	// Define a chave primária da tabela
    @Id
    // Gera o valor automaticamente com base na estratégia do banco (autoincremento no MySQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;
    
    // Coluna obrigatória, única e com tamanho máximo de 100 caracteres
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    // Coluna obrigatória com até 45 caracteres
    @Column(nullable = false, length = 45)
    private String senha;
    
    // Indica se o usuário é administrador (1) ou comum (0)
    @Column(nullable = false)
    private boolean nivel;
    
    // Nome de usuário
    @Column(nullable = false, length = 45)
    private String username;
    
    // Setor em que o usuário trabalha
    @Column(nullable = false, length = 45)
    private String setor;
 
    // Centro de custo padrão associado ao usuário
    @Column(nullable = false)
    private Integer ccpadrao;
    
    // -------------------
    
    public Integer getId_user() { return id_user; }
    public void setId_user(Integer id_user) { this.id_user = id_user; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public boolean isNivel() { return nivel; }
    public void setNivel(boolean nivel) { this.nivel = nivel; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public Integer getCcpadrao() { return ccpadrao; }
    public void setCcpadrao(Integer ccpadrao) { this.ccpadrao = ccpadrao; }
	
}
