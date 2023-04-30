package Controller;
import java.util.ArrayList;
import java.util.List;

import Model.Jogador;
import Model.Time;

public class TimeController {
    private List<Time> times = new ArrayList<>();

    public TimeController() {
        
    }

    public void adicionarTime(Time time) {
        times.add(time);
    }

    public void removerTime(Time time) {
        times.remove(time);
    }

    public void listarTimes() {
        for (int i = 0; i< times.size(); i++){
           System.out.println(times.get(i).getNome() + "Jogadores = ");
           times.get(i).getJogadores();
        }
    }

    public void adicionarJogador(Time time, Jogador jogador) {
        time.adicionarJogador(jogador);
    }

    public void removerJogador(Time time, Jogador jogador) {
        time.removerJogador(jogador);
    }

    /*public List<Jogador> listarJogadores(Time time) {
        return time.getJogadores();
    }*/
}
