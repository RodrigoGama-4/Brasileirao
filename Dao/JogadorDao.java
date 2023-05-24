package Dao;

import Model.Jogador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JogadorDao implements JogadorDaoInterface{
    private static final String URL = "jdbc:mysql://localhost:3306/eventos";
    private static final String USUARIO = "root";
    private static final String SENHA = "Rodrigama123!";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Linha adicionada

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC: " + e.getMessage());
        }
    }

    @Override
    public void adicionarJogador(Jogador jogador,int idJogador, int idTime) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "INSERT INTO Jogadores (id, nome, posicao, numero, time_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idJogador);
            statement.setString(2, jogador.getNome());
            statement.setString(3, jogador.getPosicao());
            statement.setInt(4, jogador.getNumero());
            statement.setInt(5, idTime);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar jogador ao banco de dados: " + e.getMessage());
        }
    }

    @Override
    public int obterProximoIdJogador() {
        int proximoId = 1; // Valor padrão se a tabela estiver vazia
    
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "SELECT id FROM Jogadores ORDER BY id DESC LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                proximoId = resultSet.getInt("id") + 1;
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao obter próximo ID de jogador: " + e.getMessage());
        }
    
        return proximoId;
    }
    
}
