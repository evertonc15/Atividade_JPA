package org.example.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jogador> jogadores;

    @OneToOne(mappedBy = "time", cascade = CascadeType.ALL)
    private Tecnico tecnico;

    @ManyToMany
    @JoinTable(
            name = "time_campeonato",
            joinColumns = @JoinColumn(name = "time_id"),
            inverseJoinColumns = @JoinColumn(name = "campeonato_id")
    )
    private Set<Campeonato> campeonatos;
}
