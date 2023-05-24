package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConfrontosDao implements ConfrontosDaoInterface {

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
    public void marcarConfronto(String mandante, String visitante, int idPartida) {
        int idMandante = buscarNomeTime(mandante);
        int idVisitante = buscarNomeTime(visitante);
    
        if (idMandante == -1 || idVisitante == -1) {
            System.out.println("Um ou ambos os times não foram encontrados.");
            return;
        }
    
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "INSERT INTO confrontos (id, time1_id, time2_id) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPartida);
            statement.setInt(2, idMandante);
            statement.setInt(3, idVisitante);
            statement.executeUpdate();
    
            System.out.println("Confronto marcado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao marcar confronto: " + e.getMessage());
        }
    }

    @Override
    public int obterProximoIdConfronto() {
        int proximoId = 1; // Valor padrão se não houver confrontos
    
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "SELECT MAX(id) AS max_id FROM confrontos";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                proximoId = resultSet.getInt("max_id") + 1;
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao obter próximo ID de confronto: " + e.getMessage());
        }
    
        return proximoId;
    }

    @Override
    public int buscarNomeTime(String nomeTime) {
        int idTime = -1;
    
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "SELECT id FROM times WHERE nome = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomeTime);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                idTime = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ID do time no banco de dados: " + e.getMessage());
        }
    
        return idTime;
    }
}
