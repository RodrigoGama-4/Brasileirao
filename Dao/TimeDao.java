package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.Time;


public class TimeDao implements TimeDaoInterface {

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

    @Override
    public int buscarIdTimePorNome(Time time) {
        int idTime = 1; // Valor padrão se a tabela estiver vazia
    
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "SELECT MAX(id) AS max_id FROM times";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                int maxId = resultSet.getInt("max_id");
                idTime = maxId + 1;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ID do time no banco de dados: " + e.getMessage());
        }
    
        return idTime;
    }
    
}
