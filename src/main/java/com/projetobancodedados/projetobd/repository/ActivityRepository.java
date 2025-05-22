package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}
