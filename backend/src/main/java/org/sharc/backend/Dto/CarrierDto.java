package org.sharc.backend.Dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarrierDto {
    private Long id;
    private LocalDate licenseRegistration;

    // If you need to include a list of auctions, use a simplified version (e.g., AuctionDTO)
    // However, if you want to avoid nested objects or complex structures, you might choose to exclude this
    private Set<AuctionDto> auctions;

    // Additional fields from Company that you want to include in the DTO
    // For example, name, registration date, etc.
    // Remember that Carrier extends Company, so you can include fields from Company as well
    private String name;
    private LocalDate registrationDate;

    // Add other relevant fields and methods as needed
    // Constructor with all fields
    public CarrierDto(Long id, LocalDate licenseRegistration, String name) {
        this.id = id;
        this.licenseRegistration = licenseRegistration;
//        this.auctions = auctions;
        this.name = name;
    }
}
