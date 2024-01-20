package org.sharc.backend.repository;

import org.sharc.backend.model.Auction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuctionRepository extends CrudRepository<Auction, Long> {
    public List<Auction> findAll();
}
