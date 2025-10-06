package ch.zhaw.freelance4u.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zhaw.freelance4u.model.Job;
import ch.zhaw.freelance4u.model.JobState;
import ch.zhaw.freelance4u.repository.JobRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Optional<Job> assignJob(String jobId, String freelancerId) {
        Job job = this.jobRepository.findById(jobId).orElse(null);

        if (job == null) {
            return Optional.empty();
        }

        if (job.getJobState() != JobState.NEW) {
            return Optional.empty();
        }

        job.setFreelancerId(freelancerId);
        job.setJobState(JobState.ASSIGNED);
        return Optional.of(this.jobRepository.save(job));
    }

    public Optional<Job> completeJob(String jobId, String freelancerId) {
        return this.jobRepository.findById(jobId)
                .filter(job -> job.getJobState() == JobState.ASSIGNED && job.getFreelancerId().equals(freelancerId))
                .map(job -> {
                    job.setJobState(JobState.DONE);
                    return this.jobRepository.save(job);
                });
    }
}
