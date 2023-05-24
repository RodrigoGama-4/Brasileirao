package Dao;

public interface ConfrontosDaoInterface {
    void marcarConfronto(String mandante, String visitante, int idPartida);
    int obterProximoIdConfronto();
    int buscarNomeTime(String nomeTime);
}
