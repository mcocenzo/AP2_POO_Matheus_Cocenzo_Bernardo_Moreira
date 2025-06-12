import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Inicializar o banco de dados e criar tabelas
        DatabaseInitializer.initialize();

        // 2. Instanciar DAOs
        VeiculoDAO veiculoDAO = new VeiculoDAOImpl();
        ClienteDAO clienteDAO = new ClienteDAOImpl();

        System.out.println("\n--- Testando Operações CRUD de Veículos ---");
        try {
            // Criar Veiculos
            Carro carro1 = new Carro("Toyota", "Corolla", 85000.00, 4);
            Moto moto1 = new Moto("Honda", "CBR 600RR", 50000.00, true);
            Carro carro2 = new Carro("Ford", "Mustang", 150000.00, 2);

            veiculoDAO.addVeiculo(carro1);
            veiculoDAO.addVeiculo(moto1);
            veiculoDAO.addVeiculo(carro2);
            System.out.println("Veículos adicionados ao banco de dados.");

            // Ler Veiculos
            List<Veiculo> todosVeiculos = veiculoDAO.getAllVeiculos();
            System.out.println("\nTodos os veículos no banco de dados:");
            for (Veiculo v : todosVeiculos) {
                v.detalhes();
            }

            // Ler Veículos por ID
            Veiculo veiculoBuscado = veiculoDAO.getVeiculoById(carro1.getId());
            if (veiculoBuscado != null) {
                System.out.println("\nVeículo buscado por ID (Carro1): ");
                veiculoBuscado.detalhes();
            }

            // Atualizar Veiculos
            if (veiculoBuscado instanceof Carro) {
                Carro carroParaAtualizar = (Carro) veiculoBuscado;
                carroParaAtualizar.setPreco(80000.00);
                veiculoDAO.updateVeiculo(carroParaAtualizar);
                System.out.println("\nPreço do Corolla atualizado para R$" + carroParaAtualizar.getPreco());
                veiculoBuscado = veiculoDAO.getVeiculoById(carroParaAtualizar.getId());
                veiculoBuscado.detalhes();
            }

            // Deletar Veiculo
            veiculoDAO.deleteVeiculo(moto1.getId()); // Use the ID set by DAO
            System.out.println("\nMoto " + moto1.getModelo() + " deletada do banco de dados.");

            // Verificar se o Veículo foi deletado
            todosVeiculos = veiculoDAO.getAllVeiculos();
            System.out.println("\nVeículos restantes no banco de dados:");
            if (todosVeiculos.isEmpty()) {
                System.out.println("Nenhum veículo restante.");
            } else {
                for (Veiculo v : todosVeiculos) {
                    v.detalhes();
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro no CRUD de Veículos: " + e.getMessage());
        }

        System.out.println("\n--- Testando Operações CRUD de Clientes ---");
        try {
            // Cria Clientes
            Cliente cliente1 = new Cliente("João Silva", "123.456.789-00");
            Cliente cliente2 = new Cliente("Maria Souza", "987.654.321-00");

            clienteDAO.addCliente(cliente1);
            clienteDAO.addCliente(cliente2);
            System.out.println("Clientes adicionados ao banco de dados.");

            // Ler Clientes
            List<Cliente> todosClientes = clienteDAO.getAllClientes();
            System.out.println("\nTodos os clientes no banco de dados:");
            for (Cliente c : todosClientes) {
                System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", CPF: " + c.getCpf());
            }

            // Lê os Clientes por Cpf
            Cliente clienteBuscadoCpf = clienteDAO.getClienteByCpf("123.456.789-00");
            if (clienteBuscadoCpf != null) {
                System.out.println("\nCliente buscado por CPF (João Silva): ");
                System.out.println("ID: " + clienteBuscadoCpf.getId() + ", Nome: " + clienteBuscadoCpf.getNome() + ", CPF: " + clienteBuscadoCpf.getCpf());
            }

            // Atualiza Cliente
            if (clienteBuscadoCpf != null) {
                clienteBuscadoCpf.setNome("João Silva Atualizado");
                clienteDAO.updateCliente(clienteBuscadoCpf);
                System.out.println("\nNome do cliente João Silva atualizado para: " + clienteBuscadoCpf.getNome());
                clienteBuscadoCpf = clienteDAO.getClienteById(clienteBuscadoCpf.getId());
                System.out.println("ID: " + clienteBuscadoCpf.getId() + ", Nome: " + clienteBuscadoCpf.getNome() + ", CPF: " + clienteBuscadoCpf.getCpf());
            }

            // Deletar Cliente
            clienteDAO.deleteCliente(cliente2.getId()); // Use the ID set by DAO
            System.out.println("\nCliente " + cliente2.getNome() + " deletado do banco de dados.");

            // Verifica se o Cliente foi deletado
            todosClientes = clienteDAO.getAllClientes();
            System.out.println("\nClientes restantes no banco de dados:");
            if (todosClientes.isEmpty()) {
                System.out.println("Nenhum cliente restante.");
            } else {
                for (Cliente c : todosClientes) {
                    System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", CPF: " + c.getCpf());
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro no CRUD de Clientes: " + e.getMessage());
        }
    }
}

