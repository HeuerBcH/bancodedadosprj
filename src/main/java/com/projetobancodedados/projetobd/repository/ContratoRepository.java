package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
}
