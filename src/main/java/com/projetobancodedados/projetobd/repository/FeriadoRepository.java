package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.Feriado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeriadoRepository extends JpaRepository<Feriado, Integer> {
}
