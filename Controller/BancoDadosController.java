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
            String query = "INSERT INTO Jogadores (id, nome, posicao, numero, time_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, jogador.getNextid());
            statement.setString(2, jogador.getNome());
            statement.setString(3, jogador.getPosicao());
            statement.setInt(4, jogador.getNumero());
            statement.setInt(5, idTime);
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





    public int buscarIdTimePorNome(Time nomeTime) {
        int idTime = -1;
    
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "SELECT id FROM times WHERE nome = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomeTime.getNome());
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                idTime = resultSet.getInt("id");
            }
            else{
                idTime = Time.getNextId();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ID do time no banco de dados: " + e.getMessage());
        }
    
        return idTime;
    }

    public int adicionarTime(Time time) {
        int idGerado = buscarIdTimePorNome(time);

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "INSERT IGNORE INTO times (id, nome) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idGerado);
            statement.setString(2, time.getNome());
            statement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Erro ao adicionar time ao banco de dados: " + e.getMessage());
        }

        return idGerado;
    }
}
