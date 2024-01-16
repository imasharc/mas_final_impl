package org.sharc.backend.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Manufacturer extends Company {

    @DecimalMin("100.0") // Minimum value
    @DecimalMax("999999999999.0") // Maximum value
    private double registrationBudget;
}
