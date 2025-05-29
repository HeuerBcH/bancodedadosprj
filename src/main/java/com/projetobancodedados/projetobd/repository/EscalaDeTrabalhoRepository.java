package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.EscalaDeTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscalaDeTrabalhoRepository extends JpaRepository<EscalaDeTrabalho, Integer> {
    
}
