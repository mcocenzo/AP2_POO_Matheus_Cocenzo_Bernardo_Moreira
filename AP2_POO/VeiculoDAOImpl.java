import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAOImpl implements VeiculoDAO {

    @Override
    public void addVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculos (marca, modelo, preco, tipo, portas, partida_eletrica) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, veiculo.getMarca());
            pstmt.setString(2, veiculo.getModelo());
            pstmt.setDouble(3, veiculo.getPreco());
            pstmt.setString(4, veiculo.getClass().getSimpleName()); // 'Carro' or 'Moto'

            if (veiculo instanceof Carro) {
                pstmt.setInt(5, ((Carro) veiculo).getPortas());
                pstmt.setNull(6, Types.BOOLEAN);
            } else if (veiculo instanceof Moto) {
                pstmt.setNull(5, Types.INTEGER);
                pstmt.setBoolean(6, ((Moto) veiculo).isPartidaEletrica());
            } else {
                pstmt.setNull(5, Types.INTEGER);
                pstmt.setNull(6, Types.BOOLEAN);
            }
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    veiculo.setId(generatedKeys.getInt(1)); // Set the generated ID back to the object
                }
            }
        }
    }

    @Override
    public Veiculo getVeiculoById(int id) throws SQLException {
        String sql = "SELECT * FROM veiculos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                double preco = rs.getDouble("preco");
                int veiculoId = rs.getInt("id");

                if ("Carro".equals(tipo)) {
                    int portas = rs.getInt("portas");
                    return new Carro(veiculoId, marca, modelo, preco, portas);
                } else if ("Moto".equals(tipo)) {
                    boolean partidaEletrica = rs.getBoolean("partida_eletrica");
                    return new Moto(veiculoId, marca, modelo, preco, partidaEletrica);
                }
            }
        }
        return null;
    }

    @Override
    public List<Veiculo> getAllVeiculos() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                double preco = rs.getDouble("preco");
                int veiculoId = rs.getInt("id");

                if ("Carro".equals(tipo)) {
                    int portas = rs.getInt("portas");
                    veiculos.add(new Carro(veiculoId, marca, modelo, preco, portas));
                } else if ("Moto".equals(tipo)) {
                    boolean partidaEletrica = rs.getBoolean("partida_eletrica");
                    veiculos.add(new Moto(veiculoId, marca, modelo, preco, partidaEletrica));
                }
            }
        }
        return veiculos;
    }

    @Override
    public void updateVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculos SET marca = ?, modelo = ?, preco = ?, portas = ?, partida_eletrica = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, veiculo.getMarca());
            pstmt.setString(2, veiculo.getModelo());
            pstmt.setDouble(3, veiculo.getPreco());

            if (veiculo instanceof Carro) {
                pstmt.setInt(4, ((Carro) veiculo).getPortas());
                pstmt.setNull(5, Types.BOOLEAN);
            } else if (veiculo instanceof Moto) {
                pstmt.setNull(4, Types.INTEGER);
                pstmt.setBoolean(5, ((Moto) veiculo).isPartidaEletrica());
            } else {
                pstmt.setNull(4, Types.INTEGER);
                pstmt.setNull(5, Types.BOOLEAN);
            }
            pstmt.setInt(6, veiculo.getId()); // Use the actual ID for update
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteVeiculo(int id) throws SQLException {
        String sql = "DELETE FROM veiculos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

