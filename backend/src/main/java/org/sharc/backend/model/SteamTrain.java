package org.sharc.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@DiscriminatorValue("SteamTrain")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class SteamTrain extends Train {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_steam", allocationSize = 1)
    private Long id;

    @DecimalMin("1.0") // Minimum value
    @DecimalMax("100.0") // Maximum value
    private double steamRate;

    @DecimalMin("50.0") // Minimum value
    @DecimalMax("999999.0") // Maximum value
    private double waterCapacity;

    // Static method to create a SteamTrain from a Train object
    public static SteamTrain createFromTrain(Train train, double steamRate, double waterCapacity) {
        SteamTrain st = SteamTrain.builder()
                .id(train.getId())
                .trainCode(train.getTrainCode())
                .model(train.getModel())
                .license(train.getLicense())
                .numOfWagons(train.getNumOfWagons())
                .trainLeadPrice(train.getTrainLeadPrice())
                .registrationDate(train.getRegistrationDate())
                .producedBy(train.getProducedBy())
                .steamRate(steamRate)
                .waterCapacity(waterCapacity)
                .build();
        return st;
    }
}
