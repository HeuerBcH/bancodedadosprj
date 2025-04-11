package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Marca a interface como um repositório do Spring
@Repository

//JpaRepository fornece os métodos prontos para acessar o banco (findAll, save, delete, etc.)
public interface UsersRepository extends JpaRepository<Users, Integer> {
}
