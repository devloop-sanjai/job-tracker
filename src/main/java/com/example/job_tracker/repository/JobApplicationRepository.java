package com.example.job_tracker.repository;

import com.example.job_tracker.model.JobApplication;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

}
