package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Partida;

public class PartidaController {
    private ArrayList<Partida> partidas = new ArrayList<>();

    public PartidaController() {
        
    }

    public void adicionarPartida(Partida partida) {
        System.out.println("PARTIDA ADICIONADA COM SUCESSO");
        partidas.add(partida);
    }

    public void removerPartida(Partida partida) {
        partidas.remove(partida);
    }

    public List<Partida> listarPartidas() {
        return partidas;
    }

    // Outros métodos e lógica relacionada às partidas...
}
