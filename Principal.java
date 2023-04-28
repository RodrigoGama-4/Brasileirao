import java.util.List;

import Model.Campeonato;
import Model.Jogador;
import Model.Time;

public class Principal {
    public static void main(String[] args) {

         // Instanciando o time Corinthians
        Time corinthians = new Time(1, "Corinthians", "Técnico Corinthians");
        // Instanciando os jogadores do Corinthians
        Jogador jogador1 = new Jogador(1, "Jogador 1", "Atacante", 10, corinthians);
        Jogador jogador2 = new Jogador(2, "Jogador 2", "Meio-Campista", 8, corinthians );
        Jogador jogador3 = new Jogador(3, "Jogador 3", "Zagueiro", 4, corinthians);

        
        corinthians.adicionarJogador(jogador1);
        corinthians.adicionarJogador(jogador2);
        corinthians.adicionarJogador(jogador3);

        // Instanciando o campeonato da Série A
        Campeonato campeonatoSerieA = new Campeonato("Série A");

        // Adicionando o time Corinthians ao campeonato
        campeonatoSerieA.adicionarTime(corinthians);

        // Imprimindo a lista de times do campeonato
        System.out.println("Times do Campeonato " + campeonatoSerieA.getNomeCampeonato() + ":");
        List<Time> times = campeonatoSerieA.getTimes();
        for (Time time : times) {
            System.out.println(time.getNome());
        }

        // Imprimindo a lista de jogadores do time Corinthians
        System.out.println("\nJogadores do Corinthians:");
        List<Jogador> jogadores = corinthians.getJogadores();
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNome() + " - " + jogador.getPosicao());
        }
    }
}

