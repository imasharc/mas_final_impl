package org.sharc.backend.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 255)
    private String city;

    @NotBlank
    @Size(min = 2, max = 255)
    private String street;

    @Nullable
    private Integer appartmentNumber;

    @NotBlank
    @Size(min = 6, max = 16)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company registeredFor;
}
