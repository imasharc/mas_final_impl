package org.sharc.backend.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public interface ILead {
    int getHorsePower();
    double getMaxSpeed();
}
