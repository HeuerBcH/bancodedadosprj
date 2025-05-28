package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.FuncionarioEscala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioEscalaRepository extends JpaRepository<FuncionarioEscala, FuncionarioEscala.FuncionarioEscalaId> {
}
