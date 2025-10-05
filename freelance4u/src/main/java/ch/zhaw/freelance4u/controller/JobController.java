package ch.zhaw.freelance4u.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.freelance4u.model.Job;
import ch.zhaw.freelance4u.model.JobCreateDTO;
import ch.zhaw.freelance4u.model.JobStateChangeDTO;
import ch.zhaw.freelance4u.model.JobType;
import ch.zhaw.freelance4u.repository.JobRepository;
import ch.zhaw.freelance4u.service.CompanyService;
import ch.zhaw.freelance4u.service.JobService;

@RestController
@RequestMapping("/api/job")
public class JobController {
    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobService jobService;

    @Autowired
    CompanyService companyService;

    @PostMapping()
    public ResponseEntity<Job> createJob(@RequestBody JobCreateDTO fDto) {
        try {
            Job fDAO = new Job(fDto.getTitle(), fDto.getDescription(), fDto.getJobType(), fDto.getEarnings(),
                    fDto.getCompanyId());

            if (!companyService.existsById(fDto.getCompanyId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Job savedJob = jobRepository.save(fDAO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedJob);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Job>> getAllJobs(@RequestParam(required = false) Double min,
            @RequestParam(required = false) JobType type) {
        List<Job> jobs;

        if (min != null && type != null) {
            jobs = jobRepository.findByEarningsGreaterThanAndJobType(min, type);
        } else if (min != null) {
            jobs = jobRepository.findByEarningsGreaterThan(min);
        } else if (type != null) {
            jobs = jobRepository.findByJobType(type);
        } else {
            jobs = jobRepository.findAll();
        }

        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getCompanyById(@PathVariable String id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return ResponseEntity.ok(job.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/assign")
    public ResponseEntity<Job> assignJob(@RequestBody JobStateChangeDTO dto) {
        Optional<Job> job = jobService.assignJob(dto.getJobId(), dto.getFreelancerId());

        if (job.isPresent()) {
            return ResponseEntity.ok(job.get());

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
