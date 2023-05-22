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

    //ADICIONANDO OS CONFRONTOS
    public void marcarConfronto(Partida confronto) {
        int idMandante = 0;
        int idVisitante = 0;

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            // Buscar ID do time mandante
            // Buscar ID do time mandante
            String queryMandante = "SELECT id FROM times WHERE nome = ?";
            PreparedStatement statementMandante = connection.prepareStatement(queryMandante);
            statementMandante.setString(1, confronto.getMandante().getNome());
            ResultSet resultSetMandante = statementMandante.executeQuery();

            if (resultSetMandante.next()) {
                idMandante = resultSetMandante.getInt("id");
            }

            // Buscar ID do time visitante
            String queryVisitante = "SELECT id FROM times WHERE nome = ?";
            PreparedStatement statementVisitante = connection.prepareStatement(queryVisitante);
            statementVisitante.setString(1, confronto.getVisitante().getNome());
            ResultSet resultSetVisitante = statementVisitante.executeQuery();
            if (resultSetVisitante.next()) {
                idVisitante = resultSetVisitante.getInt("id");
            }

            //Fazer a inserção ao banco de dados
            String query = "INSERT INTO confrontos (id,time1_id,time2_id) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Partida.getNextId());
            statement.setInt(2, idMandante);
            statement.setInt(3, idVisitante);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao associar IDs dos times ao confronto: " + e.getMessage());
        }
    }

    //Função responsavel para retornar na tela do usuario as informações dos times
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
    
    //Função responsavel para retornar na tela do usuario as informações dos confrontos
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
