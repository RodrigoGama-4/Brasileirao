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

    public void adicionarTimes(Time time) {
       times.add(time);
    }

    public void removerTime(Time time) {
        times.remove(time);
    }

    public void adicionarPartidas(Partida partida) {
        partidas.add(partida);
    }

    public void removerPartida(Partida partida) {
        partidas.remove(partida);
    }

    public void listarTimes(){
        for (Time todosTime: times){
            todosTime.paraString();
        }
    }

    public void listarConfrontos(){
        for (Partida todasPartidas: partidas){
            todasPartidas.paraString();
        }
    }
}

   

