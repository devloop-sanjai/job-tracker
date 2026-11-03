package com.example.job_tracker.controller;

import com.example.job_tracker.exception.ApplicationNotFoundException;
import com.example.job_tracker.model.JobApplication;
import com.example.job_tracker.repository.JobApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public JobApplication getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ApplicationNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public JobApplication update(@PathVariable Long id,
                                 @Valid @RequestBody JobApplication updated) {
        JobApplication existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException(id));
        existing.setCompany(updated.getCompany());
        existing.setRole(updated.getRole());
        existing.setStatus(updated.getStatus());
        existing.setDateApplied(updated.getDateApplied());
        return repository.save(existing);
    }

}
