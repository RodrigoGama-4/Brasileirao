package Dao;

import Model.Jogador;

public interface JogadorDaoInterface {
    void adicionarJogador(Jogador jogador, int idJogador, int idTime);
    int obterProximoIdJogador();
   
}

