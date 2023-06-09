package Model;

public class Jogador {
    private static int nextId = 0;
    private int id;
    private String nome;
    private String posicao;
    private int numero;
    private Time time;

    public Jogador(String nome, String posicao, int numero, Time time) {
        this.id = nextId;
        nextId ++;
        this.nome = nome;
        this.posicao = posicao;
        this.numero = numero;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getNextid(){
        return nextId;
    }
}


