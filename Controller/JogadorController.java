package Controller;

import java.util.List;

import Model.Jogador;

public class JogadorController {
    private List<Jogador> jogadores;

    public JogadorController(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
    
    
    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void removerJogador(Jogador jogador) {
        jogadores.remove(jogador);
    }

    public List<Jogador> listarJogadores() {
        return jogadores;
    }

    // Outros métodos e lógica relacionada aos jogadores...
}

