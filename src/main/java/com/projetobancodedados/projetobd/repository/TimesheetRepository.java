package com.projetobancodedados.projetobd.repository;

import com.projetobancodedados.projetobd.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {
}
