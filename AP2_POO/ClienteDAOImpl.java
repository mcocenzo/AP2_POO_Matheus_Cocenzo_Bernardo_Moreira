import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public void addCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, cpf) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Cliente getClienteById(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"));
            }
        }
        return null;
    }

    @Override
    public Cliente getClienteByCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"));
            }
        }
        return null;
    }

    @Override
    public List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clientes.add(new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf")));
            }
        }
        return clientes;
    }

    @Override
    public void updateCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, cpf = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setInt(3, cliente.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteCliente(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

