package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Timesheet;
import com.projetobancodedados.projetobd.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("timesheets")
public class TimesheetController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    // LISTAR TODOS os timesheets
    @GetMapping
    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

    // BUSCAR timesheet por id
    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> getTimesheetById(@PathVariable Integer id) {
        Optional<Timesheet> timesheetOpt = timesheetRepository.findById(id);
        if (timesheetOpt.isPresent()) {
            return ResponseEntity.ok(timesheetOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CRIAR novo timesheet
    @PostMapping
    public Timesheet createTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    // ATUALIZAR timesheet existente
    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> updateTimesheet(@PathVariable Integer id, @RequestBody Timesheet timesheetDetails) {
        Optional<Timesheet> timesheetOpt = timesheetRepository.findById(id);
        if (!timesheetOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Timesheet timesheet = timesheetOpt.get();

        // Atualiza os campos (usando os setters)
        timesheet.setStartTime(timesheetDetails.getStartTime());
        timesheet.setEndTime(timesheetDetails.getEndTime());
        timesheet.setJustificationLetter(timesheetDetails.getJustificationLetter());
        timesheet.setDate(timesheetDetails.getDate());
        timesheet.setEmployee(timesheetDetails.getEmployee());
        timesheet.setActivity(timesheetDetails.getActivity());
        timesheet.setContract(timesheetDetails.getContract());

        Timesheet updated = timesheetRepository.save(timesheet);
        return ResponseEntity.ok(updated);
    }

    // DELETAR timesheet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable Integer id) {
        Optional<Timesheet> timesheetOpt = timesheetRepository.findById(id);
        if (!timesheetOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        timesheetRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}