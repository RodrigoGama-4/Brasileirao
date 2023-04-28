package Model;
import java.util.ArrayList;
import java.util.List;


public class Campeonato {
    private String nome;
    private List<Time> times;
    private List<Partida> partidas;

    public Campeonato(String nome) {
        this.nome = nome;
        this.times = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    public String getNomeCampeonato() {
        return nome;
    }

    public List<Time> getTimes() {
        return times;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void adicionarTime(Time time) {
        times.add(time);
    }

    public void removerTime(Time time) {
        times.remove(time);
    }

    public void adicionarPartida(Partida partida) {
        partidas.add(partida);
    }

    public void removerPartida(Partida partida) {
        partidas.remove(partida);
    }
}


