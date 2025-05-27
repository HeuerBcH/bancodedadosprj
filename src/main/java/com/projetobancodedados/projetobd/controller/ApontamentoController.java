package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Apontamento;
import com.projetobancodedados.projetobd.repository.ApontamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apontamento")
public class ApontamentoController {

    @Autowired
    private ApontamentoRepository timesheetRepository;

    // LISTAR TODOS os timesheets
    @GetMapping
    public List<Apontamento> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

    // BUSCAR timesheet por id
    @GetMapping("/{id}")
    public ResponseEntity<Apontamento> getTimesheetById(@PathVariable Integer id) {
        Optional<Apontamento> timesheetOpt = timesheetRepository.findById(id);
        if (timesheetOpt.isPresent()) {
            return ResponseEntity.ok(timesheetOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CRIAR novo timesheet
    @PostMapping
    public Apontamento createTimesheet(@RequestBody Apontamento timesheet) {
        return timesheetRepository.save(timesheet);
    }

    // ATUALIZAR timesheet existente
    @PutMapping("/{id}")
    public ResponseEntity<Apontamento> updateTimesheet(@PathVariable Integer id, @RequestBody Apontamento timesheetDetails) {
        Optional<Apontamento> timesheetOpt = timesheetRepository.findById(id);
        if (!timesheetOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Apontamento timesheet = timesheetOpt.get();

        // Atualiza os campos (usando os setters)
        timesheet.setStartTime(timesheetDetails.getStartTime());
        timesheet.setEndTime(timesheetDetails.getEndTime());
        timesheet.setJustificationLetter(timesheetDetails.getJustificationLetter());
        timesheet.setDate(timesheetDetails.getDate());
        timesheet.setEmployee(timesheetDetails.getEmployee());
        timesheet.setActivity(timesheetDetails.getActivity());
        timesheet.setContract(timesheetDetails.getContract());

        Apontamento updated = timesheetRepository.save(timesheet);
        return ResponseEntity.ok(updated);
    }

    // DELETAR timesheet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable Integer id) {
        Optional<Apontamento> timesheetOpt = timesheetRepository.findById(id);
        if (!timesheetOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        timesheetRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}