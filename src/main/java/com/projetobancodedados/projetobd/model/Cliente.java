package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;

//Anotação que marca a classe como uma entidade JPA (será mapeada para uma tabela do banco de dados)
@Entity

public class Cliente {

	@Id
    // Gera o valor automaticamente com base na estratégia do banco
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_cliente;
	
	@Column(nullable = false, length = 45)
	private String nome_cliente;
	
	@Column(nullable = false, length = 45)
	private String rua;
	
	@Column(nullable = false, length = 20)
	private String numero;
	
	@Column(nullable = false, length = 40)
	private String bairro;
	
	@Column(nullable = false, length = 40)
	private String cidade;
	
	@Column(nullable = false, length = 20)
	private String estado;
	
	@Column(nullable = false, length = 10)
	private String cep;
	
	@Column(nullable = true, length = 40)
	private String fax;
	
	@Column(nullable = false, length = 15)
	private String telefone;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// -------------------
	
    public Integer getId_cliente() { return id_cliente; }
    public void setId_cliente(Integer id_cliente) { this.id_cliente = id_cliente; }

    public String getNome_cliente() { return nome_cliente; }
    public void setNome_cliente(String nome_cliente) { this.nome_cliente = nome_cliente; }
    
    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }
    
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    
    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
	
}
