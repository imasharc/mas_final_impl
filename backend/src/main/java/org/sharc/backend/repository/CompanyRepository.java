package org.sharc.backend.repository;

import org.sharc.backend.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
