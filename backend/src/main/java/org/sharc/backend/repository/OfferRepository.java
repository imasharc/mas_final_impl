package org.sharc.backend.repository;

import org.sharc.backend.model.Offer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long> {
    public List<Offer> findAll();

}
