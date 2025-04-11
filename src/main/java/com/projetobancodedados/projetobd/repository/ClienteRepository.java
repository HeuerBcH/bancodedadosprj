package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Marca a interface como um repositório no Spring
@Repository

//JpaRepository disponibiliza os métodos prontos para acessar o banco (findAll, save, delete, etc.)
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
