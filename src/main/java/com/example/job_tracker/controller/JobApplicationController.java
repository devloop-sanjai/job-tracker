package com.example.job_tracker.controller;

import com.example.job_tracker.model.JobApplication;
import com.example.job_tracker.repository.JobApplicationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")

public class JobApplicationController {
    private final JobApplicationRepository repository;
public JobApplicationController(JobApplicationRepository repository){
    this.repository= repository;
}


    @PostMapping
    public JobApplication create(@RequestBody JobApplication application) {
        return repository.save(application);
    }

    @GetMapping
    public List<JobApplication> getAll(){
    return  repository.findAll();
    }
}
