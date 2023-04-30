import java.util.List;

import Model.Campeonato;
import Model.Jogador;
import Model.Time;

public class Principal {
    public static void main(String[] args) {

         // Instanciando o time Corinthians
        Time corinthians = new Time("Corinthians");
        // Instanciando os jogadores do Corinthians
        Jogador jogador1 = new Jogador("Jogador 1", "Atacante", 10, corinthians);
        Jogador jogador2 = new Jogador("Jogador 2", "Meio-Campista", 8, corinthians );
        Jogador jogador3 = new Jogador("Jogador 3", "Zagueiro", 4, corinthians);

        
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
        
    }
}

