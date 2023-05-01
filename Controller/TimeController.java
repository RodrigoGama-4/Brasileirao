package Controller;
import java.util.ArrayList;

import Model.Jogador;
import Model.Time;

public class TimeController {
    public ArrayList<Time> times = new ArrayList<>();

    public TimeController() {
        
    }

    public ArrayList<Time> eita(){
        return times;
    }

    public void removerTime(Time time) {
        times.remove(time);
    }

    public void listarTimes() {
        for (int i = 0; i< times.size(); i++){
           System.out.println(times.get(i).getNome() + " Jogadores = ");
           times.get(i).getJogadores();
           System.out.println("------------------------------------------");
        }
    }

    public void adicionarJogador(Time time, Jogador jogador) {
        boolean jogadorAdicionado = false;
        
        for (int i = 0; i < times.size(); i++) {
            if (times.get(i).getNome().equals(time.getNome())) {
                times.get(i).adicionarJogador(jogador);
                jogadorAdicionado = true;
                break; 
            }
        }
        
        if (!jogadorAdicionado) {
            time.adicionarJogador(jogador);
        }
    }
    

    public void removerJogador(Time time, Jogador jogador) {
        time.removerJogador(jogador);
    }
}
