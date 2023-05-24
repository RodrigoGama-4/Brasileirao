package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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


    /*FUNÇÕES NECESSARIAS PARA ADICIONAR O JOGADOR */
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
    
    
    /*FUNÇÕES NECESSARIAS PARA ADICIONAR O TIME */
    public int buscarIdTimePorNome(Time nomeTime) {
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

    /*FUNÇÕES NECESSARIAS PARA MARCAR CONFRONTO */
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
    
    private int buscarNomeTime(String nomeTime) {
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

    /*FUNÇÕES NECESSARIAS PARA PRINTAR AS INFORMAÇÕES NA TELA */
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
