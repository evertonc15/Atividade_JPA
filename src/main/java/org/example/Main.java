package org.example;

import org.example.domain.Campeonato;
import org.example.domain.Jogador;
import org.example.domain.Tecnico;
import org.example.domain.Time;
import org.example.service.CampeonatoService;
import org.example.service.JogadorService;
import org.example.service.TecnicoService;
import org.example.service.TimeService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        TimeService timeService = new TimeService();
        JogadorService jogadorService = new JogadorService();
        TecnicoService tecnicoService = new TecnicoService();
        CampeonatoService campeonatoService = new CampeonatoService();

        Campeonato camp1 = Campeonato.builder()
                .nome("Copa InterMunicipal")
                .dataInicio(LocalDate.of(2025, 1, 10))
                .dataFim(LocalDate.of(2025, 3, 20))
                .build();
        Campeonato camp2 = Campeonato.builder()
                .nome("Campeonato Municipal de Cachoeira dos Índios")
                .dataInicio(LocalDate.of(2025, 4, 5))
                .dataFim(LocalDate.of(2025, 7, 15))
                .build();

        Jogador j1 = Jogador.builder().nome("Everton").posicao("Atacante").status("Ativo").build();
        Jogador j2 = Jogador.builder().nome("Tawan").posicao("Goleiro").status("Ativo").build();
        Jogador j3 = Jogador.builder().nome("SheldoBoy").posicao("Zagueiro").status("Lesionado").build();

        Tecnico tecnico = Tecnico.builder().nome("Professor Luciano").build();

        Time time = Time.builder()
                .nome("Merengues FC")
                .jogadores(Arrays.asList(j1, j2, j3))
                .tecnico(tecnico)
                .campeonatos(new HashSet<>(Arrays.asList(camp1, camp2)))
                .build();

        j1.setTime(time);
        j2.setTime(time);
        j3.setTime(time);
        tecnico.setTime(time);

        timeService.save(time);

        System.out.println("\n🔍 Buscar time por nome:");
        Time timeByNome = timeService.findByNome("Merengues FC");
        System.out.println("Time encontrado: " + timeByNome.getNome());

        System.out.println("\n🔍 Buscar time por nome de jogador (João):");
        Time timeByJogador = timeService.findByJogadorNome("Everton");
        System.out.println("Time do jogador Everton: " + timeByJogador.getNome());

        System.out.println("\n🔍 Buscar campeonatos que o time participa:");
        List<Campeonato> campeonatos = timeService.findCampeonatos(timeByNome.getId());
        campeonatos.forEach(c -> System.out.println(" - " + c.getNome()));

        System.out.println("\n🔍 Buscar jogador por nome (Tawan):");
        Jogador jogadorByNome = jogadorService.findByNome("Tawan");
        System.out.println("Jogador encontrado: " + jogadorByNome.getNome() +
                " (" + jogadorByNome.getStatus() + ")");

        System.out.println("\n🔍 Buscar jogadores por status (Ativo):");
        List<Jogador> jogadoresAtivos = jogadorService.findByStatus("Ativo");
        jogadoresAtivos.forEach(j -> System.out.println(" - " + j.getNome()
                + " (" + j.getPosicao() + ")"));

        System.out.println("\n🔍 Buscar jogadores por equipe (Merengues FC):");
        List<Jogador> jogadoresDoTime = jogadorService.findByTime("Merengues FC");
        jogadoresDoTime.forEach(j -> System.out.println(" - " + j.getNome()));

        System.out.println("\n🔍 Buscar equipes que participam de um campeonato " +
                "(Campeonato Municipal de Cachoeira dos Índios):");
        List<Time> timesCampeonato = campeonatoService.findTimesByNome(
                "Campeonato Municipal de Cachoeira dos Índios");
        timesCampeonato.forEach(t -> System.out.println(" - " + t.getNome()));

        System.out.println("\n🔍 Buscar o time do técnico (Professor Luciano) e seus jogadores:");
        Time timeDoTecnico = tecnicoService.findTimeByTecnicoNome("Professor Luciano");
        System.out.println("Técnico treina o time: " + timeDoTecnico.getNome());
        timeDoTecnico.getJogadores().forEach(j -> System.out.println(" - " + j.getNome()));

    }

}