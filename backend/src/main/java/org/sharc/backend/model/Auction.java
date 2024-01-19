package org.sharc.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_auction", allocationSize = 1)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "auction_requirements", joinColumns = @JoinColumn(name = "auction_id"))
    @Builder.Default
    private Set<String> requirements = new HashSet<>(Arrays.asList("Fee: $20"));

    @Future
    private LocalDate closingDate;

    @ManyToOne
    @JoinColumn(name = "carrier_id", nullable = false)
//    @JsonIgnore
    private Carrier listedBy;

    @OneToMany(mappedBy = "auction", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
//    @JsonIgnore
    private Set<Offer> offers = new HashSet<>();
}
