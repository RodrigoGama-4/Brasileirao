package Model;

import java.util.ArrayList;

import Controller.CampeonatoController;

public class TimeFactory{   
    public static Time createTime(String nome, ArrayList<Time> listaTimes){
        //INSTANCIA SINGLETON CAMPEONATO
        CampeonatoController campC = CampeonatoController.getInstance();
        for (Time time : listaTimes) {
            if (time.getNome().equals(nome)) {
                return time; // Retorna a instância existente se o nome corresponder
                
            }
        }

        // Cria uma nova instância se nenhum time com o nome for encontrado
        Time novoTime = new Time(nome);
        listaTimes.add(novoTime);
        campC.adicionarTimes(novoTime);
        return novoTime;
    }
}