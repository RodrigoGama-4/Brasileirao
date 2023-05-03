package Model;


public class Partida {
    private static int nextId = 1;
    private int id;
    private Time mandante;
    private Time visitante;
    private int golsMandante;
    private int golsVisitante;

    public Partida(Time mandante, Time visitante) {
        this.id = nextId;
        nextId ++;
        this.mandante = mandante;
        this.visitante = visitante;
        this.golsMandante = 0;
        this.golsVisitante = 0;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Partida: " +
               mandante +  " x " +
                " " + visitante;
    }
}

   

