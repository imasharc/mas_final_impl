package org.sharc.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_offer", allocationSize = 1)
    private Long id;

    @NotNull
    private LocalDateTime openingTime;

    @NotNull
    private LocalDateTime closingTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "auction_id")
//    @JsonIgnore
    private Auction auction;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
//    @JsonIgnore
    private Manufacturer manufacturer;
}
