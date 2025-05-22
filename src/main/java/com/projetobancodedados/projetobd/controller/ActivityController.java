package com.projetobancodedados.projetobd.controller;

import com.projetobancodedados.projetobd.model.Activity;
import com.projetobancodedados.projetobd.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Integer id) {
        return activityRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity) {
        return activityRepository.save(activity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Integer id, @RequestBody Activity updated) {
        return activityRepository.findById(id).map(activity -> {
            activity.setDescription(updated.getDescription());
            activity.setInternal(updated.getInternal());
            return ResponseEntity.ok(activityRepository.save(activity));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Integer id) {
        return activityRepository.findById(id).map(activity -> {
            activityRepository.delete(activity);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
