package org.sharc.backend.repository;

import org.sharc.backend.model.Train;
import org.springframework.data.repository.CrudRepository;

public interface TrainRepository extends CrudRepository<Train, Long> {
}
