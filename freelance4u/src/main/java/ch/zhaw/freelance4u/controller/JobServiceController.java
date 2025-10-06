package ch.zhaw.freelance4u.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.zhaw.freelance4u.model.Job;
import ch.zhaw.freelance4u.model.JobStateAggregationDTO;
import ch.zhaw.freelance4u.model.JobStateChangeDTO;
import ch.zhaw.freelance4u.repository.JobRepository;
import ch.zhaw.freelance4u.service.JobService;

@Controller
@RequestMapping("/api/service")
public class JobServiceController {
    @Autowired
    private JobService jobService;

    @Autowired
    private JobRepository jobRepository;

    @PutMapping("/assignJob")
    public ResponseEntity<Job> assignJob(@RequestBody JobStateChangeDTO dto) {
        Optional<Job> job = jobService.assignJob(dto.getJobId(), dto.getFreelancerId());

        if (job.isPresent()) {
            return ResponseEntity.ok(job.get());

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/completeJob")
    public ResponseEntity<Job> completeJob(@RequestBody JobStateChangeDTO dto) {
        Optional<Job> job = jobService.completeJob(dto.getJobId(), dto.getFreelancerId());

        if (job.isPresent()) {
            return ResponseEntity.ok(job.get());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/jobDashboard")
    public ResponseEntity<List<JobStateAggregationDTO>> getDashboard(@RequestParam String companyId) {
        return ResponseEntity.ok(jobRepository.getJobStateAggregation(companyId));
    }

}