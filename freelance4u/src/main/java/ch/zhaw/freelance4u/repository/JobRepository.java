package ch.zhaw.freelance4u.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.freelance4u.model.Job;
import ch.zhaw.freelance4u.model.JobType;

public interface JobRepository extends MongoRepository<Job, String> {
    List<Job> findByEarningsGreaterThan(Double earnings);

    List<Job> findByEarningsGreaterThanAndJobType(Double earnings, JobType jobType);

    List<Job> findByJobType(JobType jobType);
}
