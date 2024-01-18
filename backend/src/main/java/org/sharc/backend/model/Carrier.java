package org.sharc.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@DiscriminatorValue("Carrier")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Carrier extends Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_carrier", allocationSize = 1)
    private Long id;

    @PastOrPresent
    private LocalDate licenseRegistration;

    @OneToMany(mappedBy = "listedBy", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Auction> auctions = new HashSet<>();
}
