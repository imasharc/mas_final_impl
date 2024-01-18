package org.sharc.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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
@DiscriminatorValue("EV")
public class EV extends Train {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_ev", allocationSize = 1)
    private Long id;

    @DecimalMin("1.0") // Minimum value
    @DecimalMax("100.0") // Maximum value
    private double chargingSpeed;

    @Enumerated(EnumType.STRING)
    private BatteryType batteryType;

    // Static method to create a SteamTrain from a Train object
    public static EV createFromTrain(Train train, double chargingSpeed, BatteryType batteryType) {
        EV ev = EV.builder()
                .id(train.getId())
                .trainCode(train.getTrainCode())
                .model(train.getModel())
                .license(train.getLicense())
                .numOfWagons(train.getNumOfWagons())
                .trainLeadPrice(train.getTrainLeadPrice())
                .registrationDate(train.getRegistrationDate())
                .producedBy(train.getProducedBy())
                .chargingSpeed(chargingSpeed)
                .batteryType(batteryType)
                .build();
        return ev;
    }
}
