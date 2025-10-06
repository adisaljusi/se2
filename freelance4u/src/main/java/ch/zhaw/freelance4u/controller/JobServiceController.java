package ch.zhaw.freelance4u.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.zhaw.freelance4u.model.Job;
import ch.zhaw.freelance4u.model.JobStateChangeDTO;
import ch.zhaw.freelance4u.service.JobService;

@Controller
@RequestMapping("/api/service")
public class JobServiceController {
    @Autowired
    private JobService jobService;

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

}