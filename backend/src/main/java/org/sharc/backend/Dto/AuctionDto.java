package org.sharc.backend.Dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class AuctionDto {
    private Long id;
    private Set<String> requirements;
    private LocalDate closingDate;
    private CarrierDto listedBy;
    private Set<OfferDto> offers; // Include a set of OfferDto

    // Constructor with all fields
    public AuctionDto(Long id, Set<String> requirements, LocalDate closingDate, CarrierDto listedBy, Set<OfferDto> offers) {
        this.id = id;
        this.requirements = requirements;
        this.closingDate = closingDate;
        this.listedBy = listedBy;
        this.offers = offers;
    }

    // Other methods as needed
}
