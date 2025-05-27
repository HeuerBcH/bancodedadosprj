package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Integer> {
}
