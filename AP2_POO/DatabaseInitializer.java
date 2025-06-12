import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("DROP TABLE IF EXISTS veiculos;");
            stmt.execute("DROP TABLE IF EXISTS clientes;");
            System.out.println("Tabelas existentes removidas (se houver).");

            // Cria a tabela Veículo
            String createVeiculoTableSQL = "CREATE TABLE veiculos (\n" +
                                           "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                                           "    marca TEXT NOT NULL,\n" +
                                           "    modelo TEXT NOT NULL,\n" +
                                           "    preco REAL NOT NULL,\n" +
                                           "    tipo TEXT NOT NULL,\n" +
                                           "    portas INTEGER,\n" +
                                           "    partida_eletrica BOOLEAN\n" +
                                           ");";
            stmt.execute(createVeiculoTableSQL);
            System.out.println("Tabela 'veiculos' criada.");

            // Cria a tabela Cliente
            String createClienteTableSQL = "CREATE TABLE clientes (\n" +
                                           "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                                           "    nome TEXT NOT NULL,\n" +
                                           "    cpf TEXT UNIQUE NOT NULL\n" +
                                           ");";
            stmt.execute(createClienteTableSQL);
            System.out.println("Tabela 'clientes' criada.");

        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        initialize();
    }
}

