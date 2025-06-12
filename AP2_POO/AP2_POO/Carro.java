public class Carro extends Veiculo {
    private int portas;

    public Carro(String marca, String modelo, double preco, int portas) {
        super(marca, modelo, preco);
        this.portas = portas;
    }

    // Construtor com ID para uso do DAO
    public Carro(int id, String marca, String modelo, double preco, int portas) {
        super(id, marca, modelo, preco);
        this.portas = portas;
    }

    public int getPortas() {
        return portas;
    }

    @Override
    public void detalhes() {
        System.out.println("Carro: " + getMarca() + " " + getModelo() + ", Preço: R$" + getPreco() + ", Portas: " + portas);
    }
}

