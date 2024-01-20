package org.sharc.backend.Dto;

import lombok.*;

// Add other necessary annotations or imports

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManufacturerDto {
    private Long id;
    private String name;
//    private LocalDate registrationDate;
    // Add other fields that are relevant for the Manufacturer entity
    // Note: Only include the fields that are necessary for the API responses

    // Constructor, getters, setters, and other methods as needed
}
