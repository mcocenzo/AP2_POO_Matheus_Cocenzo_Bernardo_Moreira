import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LojaDeCarros {
    private Map<String, Veiculo> estoque;
    private Set<Cliente> clientes;

    public LojaDeCarros() {
        this.estoque = new HashMap<>();
        this.clientes = new HashSet<>();
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        if (this.estoque.containsKey(veiculo.getModelo())) {
            System.out.println("Veículo com o modelo " + veiculo.getModelo() + " já existe no estoque.");
        } else {
            this.estoque.put(veiculo.getModelo(), veiculo);
            System.out.println("Veículo " + veiculo.getMarca() + " " + veiculo.getModelo() + " adicionado ao estoque.");
        }
    }

    public void listarVeiculosDisponiveis() {
        if (estoque.isEmpty()) {
            System.out.println("Estoque vazio.");
            return;
        }
        System.out.println("\nVeículos disponíveis no estoque:");
        for (Veiculo veiculo : estoque.values()) {
            veiculo.detalhes();
        }
    }

    public Veiculo buscarVeiculo(String modelo) {
        return estoque.get(modelo);
    }

    public void removerVeiculo(Veiculo veiculo) {
        if (this.estoque.containsValue(veiculo)) {
            this.estoque.remove(veiculo.getModelo());
            System.out.println("Veículo " + veiculo.getMarca() + " " + veiculo.getModelo() + " removido do estoque.");
        } else {
            System.out.println("Veículo não encontrado no estoque.");
        }
    }

    public void adicionarCliente(Cliente cliente) {
        if (this.clientes.add(cliente)) {
            System.out.println("Cliente " + cliente.getNome() + " adicionado à lista de clientes.");
        } else {
            System.out.println("Cliente " + cliente.getNome() + " já existe na lista de clientes.");
        }
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("\nClientes cadastrados:");
        for (Cliente cliente : clientes) {
            System.out.println("Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
        }
    }
}

