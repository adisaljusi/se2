package ch.zhaw.freelance4u.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.freelance4u.model.Job;
import ch.zhaw.freelance4u.model.JobCreateDTO;
import ch.zhaw.freelance4u.repository.JobRepository;

@RestController
@RequestMapping("/api/job")
public class JobController {
    @Autowired
    JobRepository jobRepository;

    @PostMapping()
    public ResponseEntity<Job> createJob(@RequestBody JobCreateDTO fDto) {
        try {
            Job fDAO = new Job(fDto.getTitle(), fDto.getDescription(), fDto.getJobType(), fDto.getEarnings(),
                    fDto.getCompanyId());
            Job savedJob = jobRepository.save(fDAO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedJob);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> companies = jobRepository.findAll();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getCompanyById(@PathVariable String id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return ResponseEntity.ok(job.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
