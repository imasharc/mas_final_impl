package org.sharc.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@DiscriminatorValue("Manufacturer")
public class Manufacturer extends Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_manufacturer", allocationSize = 1)    private Long id;

    @DecimalMin("100.0") // Minimum value
    @DecimalMax("999999999999.0") // Maximum value
    private double registrationBudget;

    @OneToMany(mappedBy = "producedBy", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Train> trains = new HashSet<>();
}
