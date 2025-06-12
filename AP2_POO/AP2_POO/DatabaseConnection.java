import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:lojacarros.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC"); // Carrega o driver SQLite
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC SQLite não encontrado: " + e.getMessage());
            throw new SQLException("Driver JDBC SQLite não encontrado.", e);
        }
        return DriverManager.getConnection(URL);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}

