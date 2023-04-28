package Controller;
import java.util.List;

import Model.Jogador;
import Model.Time;

public class TimeController {
    private List<Time> times;

    public TimeController(List<Time> times) {
        this.times = times;
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

    public void adicionarJogador(Time time, Jogador jogador) {
        time.adicionarJogador(jogador);
    }

    public void removerJogador(Time time, Jogador jogador) {
        time.removerJogador(jogador);
    }

    public List<Jogador> listarJogadores(Time time) {
        return time.getJogadores();
    }
}
