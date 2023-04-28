package Controller;

import java.util.List;

import Model.Partida;
import Model.Time;


public class CampeonatoController {
    private List<Time> times;
    private List<Partida> partidas;

    public CampeonatoController(List<Time> times, List<Partida> partidas) {
        this.times = times;
        this.partidas = partidas;
    }

    public void adicionarTime(Time time) {
        times.add(time);
    }

    public void removerTime(Time time) {
        times.remove(time);
    }

    public List<Time> listarTimes() {
        return times;
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

    // Outros métodos e lógica relacionada ao campeonato...
}

   

