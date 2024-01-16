package org.sharc.backend.repository;

import org.sharc.backend.model.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    @Query("from Company as c left join fetch c.addresses where c.id = :id")
    public Optional<Company> findById(@Param("id") Long id);
}
