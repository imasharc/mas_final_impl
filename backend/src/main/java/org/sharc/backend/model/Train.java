package org.sharc.backend.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuperBuilder
@ToString
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_train", allocationSize = 1)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    @Size(min = 2, max = 255)
    private String trainCode;

    @NotBlank
    @Column(unique = true)
    @Size(min = 2, max = 255)
    private String model;

    @NotBlank
    @Column(unique = true, nullable = false)
    @Size(min = 2, max = 255)
    private String license;

    @Nullable
    private Integer numOfWagons;

    @DecimalMin("100.0") // Minimum value
    @DecimalMax("999999999999.0") // Maximum value
    private double trainLeadPrice;

    @PastOrPresent
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer producedBy;

    public double getTrainLeadPrice(double trainLeadPrice) {
        if (registrationDate.isBefore(LocalDate.now().minusYears(20))) {
            return trainLeadPrice - 1000;
        }
        if (model.contains("HQ")) {
            return trainLeadPrice + 500;
        }
        return trainLeadPrice;
    }

    public Train(Long id, String trainCode, String model, String license, int numOfWagons, double trainLeadPrice, LocalDate registrationDate, Manufacturer producedBy) {
        this.id = id;
        this.trainCode = trainCode;
        this.model = model;
        this.license = license;
        this.numOfWagons = numOfWagons;
        this.trainLeadPrice = trainLeadPrice;
        this.registrationDate = registrationDate;
        this.producedBy = producedBy;
    }
}
