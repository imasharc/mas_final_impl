package org.sharc.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@DiscriminatorValue("CombustionTrain")
public class CombustionTrain extends Train implements ILead {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_combustion", allocationSize = 1)
    private Long id;

    @DecimalMin("50.0") // Minimum value
    @DecimalMax("999999.0") // Maximum value
    private double combustionCapacity;

    @DecimalMin("0") // Minimum value
    @DecimalMax("12000") // Maximum value
    private int horsePower;

    @DecimalMin("0.0") // Minimum value
    @DecimalMax("400.0") // Maximum value
    private double maxSpeed;

    @Override
    public int getHorsePower() {
        return horsePower;
    }

    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }

    // Static method to create a SteamTrain from a Train object
    public static CombustionTrain createFromTrain(Train train, double combustionCapacity, int horsePower, double maxSpeed) {
        CombustionTrain ct = CombustionTrain.builder()
                .id(train.getId())
                .trainCode(train.getTrainCode())
                .model(train.getModel())
                .license(train.getLicense())
                .numOfWagons(train.getNumOfWagons())
                .trainLeadPrice(train.getTrainLeadPrice())
                .registrationDate(train.getRegistrationDate())
                .producedBy(train.getProducedBy())
                .combustionCapacity(combustionCapacity)
                .horsePower(horsePower)
                .maxSpeed(maxSpeed)
                .build();
        return ct;
    }
}
