package ch.zhaw.freelance4u.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.freelance4u.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {
}
