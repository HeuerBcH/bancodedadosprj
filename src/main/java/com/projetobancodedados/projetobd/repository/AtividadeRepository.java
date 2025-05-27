package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {
}
