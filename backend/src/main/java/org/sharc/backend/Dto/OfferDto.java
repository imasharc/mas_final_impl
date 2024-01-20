package org.sharc.backend.Dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class OfferDto {
    private Long id;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;

    // Include identifiers or DTOs for related entities instead of the full objects
    private LocalDate auction;
    private String carrier;
    private String manufacturer;

    // You can also include more detailed information if needed, like names or specific fields from related entities
    // For example:
    // private String manufacturerName;

    // Constructor, getters, setters, and other methods as needed
    // Constructor with all fields
    public OfferDto(Long id, LocalDateTime openingTime, LocalDateTime closingTime, LocalDate auction, String carrier, String manufacturer) {
        this.id = id;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.auction = auction;
        this.carrier = carrier;
        this.manufacturer = manufacturer;
    }
}
