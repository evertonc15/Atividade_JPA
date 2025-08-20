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
@NamedQueries({
        @NamedQuery(
                name = "Time.findByNome",
                query = "SELECT t FROM Time t WHERE t.nome = :nome"
        ),
        @NamedQuery(
                name = "Time.findByJogadorNome",
                query = "SELECT t FROM Time t JOIN t.jogadores j WHERE j.nome = :nomeJogador"
        ),
        @NamedQuery(
                name = "Time.findCampeonatos",
                query = "SELECT c FROM Time t JOIN t.campeonatos c WHERE t.id = :timeId"
        )
})
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jogador> jogadores;

    @OneToOne(mappedBy = "time", cascade = CascadeType.ALL)
    private Tecnico tecnico;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "time_campeonato",
            joinColumns = @JoinColumn(name = "time_id"),
            inverseJoinColumns = @JoinColumn(name = "campeonato_id")
    )
    private Set<Campeonato> campeonatos;

}
