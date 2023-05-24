package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DadosDao implements DadosDaoInterface {

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
    public void mostrarTimes() {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "SELECT * FROM times";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
    
            System.out.println("Times:");
            System.out.println("-------");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                System.out.printf("ID: %d | Nome: %s%n", id, nome);
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao exibir times: " + e.getMessage());
        }
    }

    @Override
    public void mostrarConfrontos() {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "SELECT c.id, t1.nome AS mandante, t2.nome AS visitante FROM confrontos c " +
                    "INNER JOIN times t1 ON c.time1_id = t1.id " +
                    "INNER JOIN times t2 ON c.time2_id = t2.id";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
    
            System.out.println("Confrontos:");
            System.out.println("-----------");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String mandante = resultSet.getString("mandante");
                String visitante = resultSet.getString("visitante");
                System.out.printf("ID: %d | Mandante: %s | Visitante: %s%n", id, mandante, visitante);
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao exibir confrontos: " + e.getMessage());
        }
    }
    
}
