import java.sql.SQLException;
import java.util.List;

public interface VeiculoDAO {
    void addVeiculo(Veiculo veiculo) throws SQLException;
    Veiculo getVeiculoById(int id) throws SQLException;
    List<Veiculo> getAllVeiculos() throws SQLException;
    void updateVeiculo(Veiculo veiculo) throws SQLException;
    void deleteVeiculo(int id) throws SQLException;
}

