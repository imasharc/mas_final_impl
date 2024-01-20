package org.sharc.backend.controller;

import org.sharc.backend.Dto.AuctionDto;
import org.sharc.backend.Dto.CarrierDto;
import org.sharc.backend.Dto.OfferDto;
import org.sharc.backend.model.Auction;
import org.sharc.backend.model.Offer;
import org.sharc.backend.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auctions")
public class AuctionController {
    @Autowired
    private AuctionRepository auctionRepository;

    @GetMapping
    public ResponseEntity<Iterable<AuctionDto>> getAllAuctions() {
        Iterable<Auction> auctions = auctionRepository.findAll();
        Iterable<AuctionDto> auctionDtos = StreamSupport.stream(auctions.spliterator(), false)
                .map(auction -> convertToAuctionDto(auction, false)) // Do not include offers
                .collect(Collectors.toList());
        return ResponseEntity.ok(auctionDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuctionDto> getAuctionById(@PathVariable Long id) {
        return auctionRepository.findById(id)
                .map(auction -> convertToAuctionDto(auction, true)) // Include offers
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private Iterable<AuctionDto> convertToAuctionDtos(Iterable<Auction> auctions) {
        return StreamSupport.stream(auctions.spliterator(), false)
                .map(auction -> convertToAuctionDto(auction, true))
                .collect(Collectors.toList());
    }

    private AuctionDto convertToAuctionDto(Auction auction, boolean includeOffers) {
        CarrierDto carrierDto = new CarrierDto(
                auction.getListedBy().getId(),
                auction.getListedBy().getLicenseRegistration(),
                // Populate other fields here:
                auction.getListedBy().getName() // Assuming getName() is a method in your Carrier entity
                // ... other fields as needed
        );

        Set<OfferDto> offerDtos = includeOffers ? convertToOfferDtos(auction.getOffers()) : new HashSet<>();

        return new AuctionDto(
                auction.getId(),
                new HashSet<>(auction.getRequirements()),
                auction.getClosingDate(),
                carrierDto,
                offerDtos
                // ... populate other auction fields if necessary
        );
    }

    private Set<OfferDto> convertToOfferDtos(Set<Offer> offers) {
        return offers.stream()
                .map(this::convertToOfferDto)
                .collect(Collectors.toSet());
    }

    private OfferDto convertToOfferDto(Offer offer) {
//        AuctionDto auctionDto = convertToAuctionDto(offer.getAuction());

        // Method to convert Offer to OfferDto
        // Example:
        return new OfferDto(
                offer.getId(),
                offer.getOpeningTime(),
                offer.getClosingTime(),
                offer.getAuction().getClosingDate(),
                offer.getAuction().getListedBy().getName(),
                offer.getManufacturer().getName()
                // ... other offer fields
        );
    }
}
