package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.Partida;
import Model.Jogador;


public class BancoDadosController {
    private static final String URL = "jdbc:mysql://localhost:3306/brasileirao";
    private static final String USUARIO = "root";
    private static final String SENHA = "Rodrigama123!";

    public void adicionarJogador(Jogador jogador) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "INSERT INTO Jogadores (nome, posicao, numero, time_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, jogador.getNome());
            statement.setString(2, jogador.getPosicao());
            statement.setInt(3, jogador.getNumero());
            statement.setInt(4, jogador.getTime().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar jogador ao banco de dados: " + e.getMessage());
        }
    }

    public void adicionarConfronto(Partida confronto) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "INSERT INTO Confrontos (time1_id, time2_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, confronto.getMandante().getId());
            statement.setInt(2, confronto.getVisitante().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar confronto ao banco de dados: " + e.getMessage());
        }
    }
}

