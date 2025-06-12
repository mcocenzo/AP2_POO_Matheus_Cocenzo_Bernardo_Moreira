public class Moto extends Veiculo {
    private boolean partidaEletrica;

    public Moto(String marca, String modelo, double preco, boolean partidaEletrica) {
        super(marca, modelo, preco);
        this.partidaEletrica = partidaEletrica;
    }

    // Construtor com ID para o DAO
    public Moto(int id, String marca, String modelo, double preco, boolean partidaEletrica) {
        super(id, marca, modelo, preco);
        this.partidaEletrica = partidaEletrica;
    }

    public boolean isPartidaEletrica() {
        return partidaEletrica;
    }

    @Override
    public void detalhes() {
        System.out.println("Moto: " + getMarca() + " " + getModelo() + ", Preço: R$" + getPreco() + ", Partida Elétrica: " + (partidaEletrica ? "Sim" : "Não"));
    }
}

