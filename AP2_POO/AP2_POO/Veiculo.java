public abstract class Veiculo implements Vendavel {
    protected int id;
    protected String marca;
    protected String modelo;
    protected double preco;

    public Veiculo(String marca, String modelo, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.preco = preco;
    }

    // Construtor com ID para o DAO
    public Veiculo(int id, String marca, String modelo, double preco) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public abstract void detalhes();

    @Override
    public void vender() {
        System.out.println("Veículo " + marca + " " + modelo + " vendido por R$" + preco);
    }
}

