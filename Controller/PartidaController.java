package Controller;

import java.util.ArrayList;

import Model.Partida;

public class PartidaController {
    private ArrayList<Partida> partidas = new ArrayList<>();

    public PartidaController() {
        
    }

    public void adicionarPartida(Partida partida) {
        System.out.println("PARTIDA ENTRE O TIME: " + partida.getMandante().getNome() +" E O " + partida.getVisitante().getNome() + " ADICIONADA COM SUCESSO");
        partidas.add(partida);
    }

    public void removerPartida(Partida partida) {
        partidas.remove(partida);
    }

    public void listarPartidas() {
        System.out.println("-------------------- TODOS OS JOGOS ------------------------");
        for (Partida parti : partidas){
            System.out.println("Time 1: " + parti.getMandante().getNome() + " X " + "Time 2: "  + parti.getVisitante().getNome());
        }
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    // Outros métodos e lógica relacionada às partidas...
}
