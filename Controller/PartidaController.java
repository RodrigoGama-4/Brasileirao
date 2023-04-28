package Controller;

import java.util.List;

import Model.Partida;

public class PartidaController {
    private List<Partida> partidas;

    public PartidaController(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public void adicionarPartida(Partida partida) {
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
