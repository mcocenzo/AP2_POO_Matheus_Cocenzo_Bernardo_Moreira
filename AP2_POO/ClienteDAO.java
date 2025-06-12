import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {
    void addCliente(Cliente cliente) throws SQLException;
    Cliente getClienteById(int id) throws SQLException;
    Cliente getClienteByCpf(String cpf) throws SQLException;
    List<Cliente> getAllClientes() throws SQLException;
    void updateCliente(Cliente cliente) throws SQLException;
    void deleteCliente(int id) throws SQLException;
}

