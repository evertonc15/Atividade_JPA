package org.example;

import org.example.domain.Campeonato;
import org.example.domain.Jogador;
import org.example.domain.Tecnico;
import org.example.domain.Time;
import org.example.service.TimeService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//
//        TimeService timeService = new TimeService();
//
//        // Criando Campeonatos
//        Campeonato camp1 = Campeonato.builder()
//                .nome("Copa Nacional")
//                .dataFim(LocalDate.of(2025, 1, 10))
//                .dataFim(LocalDate.of(2025, 3, 20))
//                .build();
//        Campeonato camp2 = Campeonato.builder()
//                .nome("Liga dos Campeões")
//                .dataInicio(LocalDate.of(2025, 4, 5))
//                .dataFim(LocalDate.of(2025, 7, 15))
//                .build();
//
//        // Criando Jogadores
//        Jogador j1 = Jogador.builder().nome("Everton").posicao("Atacante").status("Ativo").build();
//        Jogador j2 = Jogador.builder().nome("Shelldon").posicao("Goleiro").status("Ativo").build();
//        Jogador j3 = Jogador.builder().nome("Tawan").posicao("Zagueiro").status("Lesionado").build();
//
//        // Criando Técnico
//        Tecnico tecnico = Tecnico.builder().nome("Professor Luciano").build();
//
//        // Criando time
//        Time time = Time.builder()
//                .nome("Bons de Bola")
//                .jogadores(Arrays.asList(j1, j2, j3))
//                .tecnico(tecnico)
//                .campeonatos(new HashSet<>(Arrays.asList(camp1, camp2)))
//                .build();
//
//        j1.setTime(time);
//        j2.setTime(time);
//        j3.setTime(time);
//        tecnico.setTime(time);
//
//        timeService.save(time);
//
//        System.out.println("\n🔍 Buscar time por nome:");
//        Time timeByNome = timeService.findByNome("Bons de Bola");
//        System.out.println(timeByNome.getNome());
//
//        System.out.println("\n🔍 Buscar time por nome de jogador (João):");
//        Time timeByJogador = timeService.findByJogadorNome("João");
//        System.out.println(timeByJogador.getNome());
//
//        System.out.println("\n🔍 Buscar campeonatos que o time participa:");
//        List<Campeonato> campeonatos = timeService.findCampeonatos(timeByNome.getId());
//        campeonatos.forEach(c -> System.out.println(c.getNome()));
    }

}