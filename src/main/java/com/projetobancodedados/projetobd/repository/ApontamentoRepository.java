package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.Apontamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApontamentoRepository extends JpaRepository<Apontamento, Integer> {
}
