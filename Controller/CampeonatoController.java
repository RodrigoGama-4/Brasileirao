package Controller;

import java.util.ArrayList;

import Model.Partida;
import Model.Time;


public class CampeonatoController {
    private ArrayList<Time> times = new ArrayList<>();
    private ArrayList<Partida> partidas = new ArrayList<>();

    private static CampeonatoController instance;

    private CampeonatoController() {
    
    }

    public static synchronized CampeonatoController getInstance() {
        if (instance == null) {
            instance = new CampeonatoController();
        }
        return instance;
    }

    public void adicionarTimes(ArrayList<Time> time) {
        for (Time todosTimes: time){
            times.add(todosTimes);
        }
    }

    public void removerTime(Time time) {
        times.remove(time);
    }

    public void adicionarPartidas(ArrayList<Partida> partida) {
        for (Partida todasPartidas: partida){
            partidas.add(todasPartidas);
        }
    }

    public void removerPartida(Partida partida) {
        partidas.remove(partida);
    }
}

   

