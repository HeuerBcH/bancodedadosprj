package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Timesheet;
import com.projetobancodedados.projetobd.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheet")
public class TimesheetController {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @GetMapping
    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> getTimesheetById(@PathVariable Integer id) {
        return timesheetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Timesheet createTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> updateTimesheet(@PathVariable Integer id, @RequestBody Timesheet updated) {
        return timesheetRepository.findById(id).map(timesheet -> {
            timesheet.setStartTime(updated.getStartTime());
            timesheet.setEndTime(updated.getEndTime());
            timesheet.setJustificationLetter(updated.getJustificationLetter());
            timesheet.setDate(updated.getDate());
            timesheet.setEmployee(updated.getEmployee());
            timesheet.setActivity(updated.getActivity());
            timesheet.setContract(updated.getContract());
            return ResponseEntity.ok(timesheetRepository.save(timesheet));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable Integer id) {
        return timesheetRepository.findById(id).map(timesheet -> {
            timesheetRepository.delete(timesheet);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
