package Model;

import java.util.List;

import Controller.TimeController;

public class TimeFactory{
    private static TimeController time;
   

    public static Time createTime(String nome){
        List<Time> listaTimes = time.times;
        Time a = null;

        if (listaTimes.size() == 0){
            a = new Time(nome);
        }

        for (int i = 0; i < listaTimes.size(); i++) {
            if (listaTimes.get(i).getNome().equals(nome)) {
                a = listaTimes.get(i);
            }
            else{
                a = listaTimes.get(i);
            }
        }
        
        return a;

        
    }
}
