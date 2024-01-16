package org.sharc.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
    @SequenceGenerator(name = "entity_seq_generator", sequenceName = "entity_seq_acc", allocationSize = 1)
    private Long id;

    @Size(min = 2)
    private String login;

    @NotBlank
    @Size(min = 2)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Company company;
}