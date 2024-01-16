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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_address", allocationSize = 1)    private Long id;

    @NotBlank
    @Size(min = 2, max = 255)
    private static String country = "Poland";

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

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", appartmentNumber='" + appartmentNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", registeredFor='" + registeredFor + '\'' +
                '}';
    }
}
