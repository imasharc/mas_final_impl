package org.sharc.backend.repository;


import org.sharc.backend.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
