package Model;

import java.util.ArrayList;

import Controller.TimeController;

public class TimeFactory{   
    public static Time createTime(String nome, ArrayList<Time> listaTimes){
        
        for (Time time : listaTimes) {
            if (time.getNome().equals(nome)) {
                System.out.println("eiiita");
                return time; // Retorna a instância existente se o nome corresponder
            }
        }

        // Cria uma nova instância se nenhum time com o nome for encontrado
        Time novoTime = new Time(nome);
        listaTimes.add(novoTime);
        System.out.println("oi");
        return novoTime;
        
        
    }
}