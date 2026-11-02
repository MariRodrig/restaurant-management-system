package cardapio;

public class Lanche implements ItemCardapio {

    private String nome;
    private double preco;
    private String ingredientes;
    private boolean queijo = true;
    private boolean alface = true;
    private boolean tomate = true;
    private boolean cebola = true;

    public Lanche(String nome, double preco) {
        setNome(nome);
        setPreco(preco);
    }

    public Lanche(String nome, double preco, String ingredientes) {
        setNome(nome);
        setPreco(preco);
        setIngredientes(ingredientes);
    }

    public boolean isQueijo() {
        return queijo;
    }

    public void setQueijo(boolean queijo) {
        this.queijo = queijo;
    }

    public boolean isAlface() {
        return alface;
    }

    public void setAlface(boolean alface) {
        this.alface = alface;
    }

    public boolean isTomate() {
        return tomate;
    }

    public void setTomate(boolean tomate) {
        this.tomate = tomate;
    }

    public boolean isCebola() {
        return cebola;
    }

    public void setCebola(boolean cebola) {
        this.cebola = cebola;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + String.format("%.2f", preco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do lanche inválido.");
        }
        this.nome = nome;
    }

    @Override
    public String getDescricao() {
        String desc = nome;

        if (!queijo) desc += " sem queijo";
        if (!alface) desc += " sem alface";
        if (!tomate) desc += " sem tomate";
        if (!cebola) desc += " sem cebola";

        return desc;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero!");
        }
        this.preco = preco;
    }
}
