package org.sharc.backend.controller;

import org.sharc.backend.Dto.AuctionDto;
import org.sharc.backend.Dto.CarrierDto;
import org.sharc.backend.Dto.ManufacturerDto;
import org.sharc.backend.Dto.OfferDto;
import org.sharc.backend.model.*;
import org.sharc.backend.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {
    @Autowired
    private OfferRepository offerRepository;

    @GetMapping
    public ResponseEntity<Iterable<OfferDto>> getAllOffers() {
        Iterable<Offer> offers = offerRepository.findAll();
        Iterable<OfferDto> offerDtos = StreamSupport.stream(offers.spliterator(), false)
                .map(this::convertToOfferDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(offerDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable Long id) {
        return offerRepository.findById(id)
                .map(this::convertToOfferDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private OfferDto convertToOfferDto(Offer offer) {
        AuctionDto auctionDto = convertToAuctionDto(offer.getAuction());
        ManufacturerDto manufacturerDto = convertToManufacturerDto(offer.getManufacturer());

        return new OfferDto(
                offer.getId(),
                offer.getOpeningTime(),
                offer.getClosingTime(),
                auctionDto.getClosingDate(),
                auctionDto.getListedBy().getName(),
                offer.getManufacturer().getName()
        );
    }

    private AuctionDto convertToAuctionDto(Auction auction) {
        Set<Long> offerIds = auction.getOffers().stream()
                .map(Offer::getId)
                .collect(Collectors.toSet());

        CarrierDto carrierDto = convertToCarrierDto(auction.getListedBy());

        return new AuctionDto(
                auction.getId(),
                new HashSet<>(auction.getRequirements()),
                auction.getClosingDate(),
                carrierDto,
                null
        );
    }

    private CarrierDto convertToCarrierDto(Carrier carrier) {
        // Convert Carrier to CarrierDto
        return new CarrierDto(
                carrier.getId(),
                carrier.getLicenseRegistration(),
                carrier.getName()
                // ... other necessary fields
        );
    }

    private ManufacturerDto convertToManufacturerDto(Manufacturer manufacturer) {
        // Convert Manufacturer to ManufacturerDto
        return new ManufacturerDto(
                manufacturer.getId(),
                manufacturer.getName()
                // ... other necessary fields
        );
    }
}
