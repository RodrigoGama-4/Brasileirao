package Controller;
import java.util.ArrayList;
import java.util.List;

import Model.Jogador;
import Model.Time;

public class TimeController {
    public List<Time> times = new ArrayList<>();

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
           System.out.println(times.get(i).getNome() + " Jogadores = ");
           times.get(i).getJogadores();
           System.out.println(times.size());
        }
    }

    public void adicionarJogador(Time time, Jogador jogador) {
        boolean jogadorAdicionado = false;
        
        for (int i = 0; i < times.size(); i++) {
            if (times.get(i).getNome().equals(time.getNome())) {
                times.get(i).adicionarJogador(jogador);
                jogadorAdicionado = true;
                break; // Interrompe o loop após adicionar o jogador
            }
        }
        
        if (!jogadorAdicionado) {
            time.adicionarJogador(jogador);
        }
    }
    

    public void removerJogador(Time time, Jogador jogador) {
        time.removerJogador(jogador);
    }

    /*public List<Jogador> listarJogadores(Time time) {
        return time.getJogadores();
    }*/
}
