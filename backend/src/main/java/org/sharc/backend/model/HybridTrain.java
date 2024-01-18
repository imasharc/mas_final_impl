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
@DiscriminatorValue("HybridTrain")
public class HybridTrain extends EV implements ILead {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_hybrid", allocationSize = 1)
    private Long id;

    private boolean batteryIntegration;

    @DecimalMin("1") // Minimum value
    @DecimalMax("100") // Maximum value
    private int batteryToCombustionRate;

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
    public static HybridTrain createFromTrain(EV evTrain, boolean batteryIntegration, int batteryToCombustionRate, int horsePower, double maxSpeed) {
        HybridTrain ht = HybridTrain.builder()
                .id(evTrain.getId())
                .trainCode(evTrain.getTrainCode())
                .model(evTrain.getModel())
                .license(evTrain.getLicense())
                .numOfWagons(evTrain.getNumOfWagons())
                .trainLeadPrice(evTrain.getTrainLeadPrice())
                .registrationDate(evTrain.getRegistrationDate())
                .producedBy(evTrain.getProducedBy())
                .chargingSpeed(evTrain.getChargingSpeed())
                .batteryType(evTrain.getBatteryType())
                .batteryIntegration(batteryIntegration)
                .batteryToCombustionRate(batteryToCombustionRate)
                .horsePower(horsePower)
                .maxSpeed(maxSpeed)
                .build();
        return ht;
    }
}
