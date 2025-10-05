package ch.zhaw.freelance4u.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.freelance4u.model.Job;

public interface JobRepository extends MongoRepository<Job, String> {
}
