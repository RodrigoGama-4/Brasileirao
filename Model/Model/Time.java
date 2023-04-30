package Model;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private static int nextId = 1;
    private int id;
    private String nome;
    private List<Jogador> jogadores;

    public Time(String nome) {
        this.id = nextId;
        nextId ++;
        this.nome = nome;
      
        this.jogadores = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }


    public void getJogadores() {
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNome() + " - " + jogador.getPosicao());
        }
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }    
}
