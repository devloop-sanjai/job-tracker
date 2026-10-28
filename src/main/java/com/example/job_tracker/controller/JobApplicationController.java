package com.example.job_tracker.controller;

import com.example.job_tracker.model.JobApplication;
import com.example.job_tracker.repository.JobApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")

public class JobApplicationController {
    private final JobApplicationRepository repository;
public JobApplicationController(JobApplicationRepository repository){
    this.repository= repository;
}


    @PostMapping
    public JobApplication create(@Valid @RequestBody JobApplication application) {
        return repository.save(application);
    }

    @GetMapping
    public List<JobApplication> getAll(){
    return  repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getById(@PathVariable Long id) {
        Optional<JobApplication> found = repository.findById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(found.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> update(@Valid @PathVariable Long id,
                                                 @RequestBody JobApplication updated) {
        Optional<JobApplication> found = repository.findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        JobApplication existing = found.get();
        existing.setCompany(updated.getCompany());
        existing.setRole(updated.getRole());
        existing.setStatus(updated.getStatus());
        existing.setDateApplied(updated.getDateApplied());
        return ResponseEntity.ok(repository.save(existing));
    }

}
