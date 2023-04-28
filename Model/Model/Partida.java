package Model;

import java.util.Date;

public class Partida {
    private int id;
    private Date data;
    private Time mandante;
    private Time visitante;
    private int golsMandante;
    private int golsVisitante;

    public Partida(int id, Date data, Time mandante, Time visitante) {
        this.id = id;
        this.data = data;
        this.mandante = mandante;
        this.visitante = visitante;
        this.golsMandante = 0;
        this.golsVisitante = 0;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public Time getMandante() {
        return mandante;
    }

    public Time getVisitante() {
        return visitante;
    }

    public int getGolsMandante() {
        return golsMandante;
    }

    public void setGolsMandante(int golsMandante) {
        this.golsMandante = golsMandante;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(int golsVisitante) {
        this.golsVisitante = golsVisitante;
    }
}

   

