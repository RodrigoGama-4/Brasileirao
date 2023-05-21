package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Partida;
import Model.Time;
import Model.Jogador;

public class BancoDadosController {
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

    public void adicionarJogador(Jogador jogador, int idTime) {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "INSERT INTO Jogadores (nome, posicao, numero, time_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, jogador.getNome());
            statement.setString(2, jogador.getPosicao());
            statement.setInt(3, jogador.getNumero());
            statement.setInt(4, idTime);
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

    public int adicionarTime(Time time) {
        int idGerado = -1; // Valor padrão caso não seja possível obter o ID gerado

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "INSERT INTO times (nome) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, time.getNome());
            statement.executeUpdate();

            // Obtém o ID gerado
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGerado = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar time ao banco de dados: " + e.getMessage());
        }

        return idGerado;
    }
}
