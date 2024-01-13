package org.sharc.impl.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
