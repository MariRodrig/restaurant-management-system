package pedido;

import cardapio.ItemCardapio;
import cardapio.Lanche;
import lombok.Setter;

import java.util.ArrayList;

public class Pedido {

    private int identificador;
    private String nome;

    public Pedido(int identificador, String nome) {
        this.identificador = identificador;
        this.setNome(nome);
    }

    @Override
    public String toString() {
        return "Pedido nº " + identificador +
                "\nCliente: " + nome +
                "\nItens: " + itens +
                "\nTotal: R$ " + calcularTotal();
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente inválido.");
        }
        this.nome = nome;
    }

    private ArrayList<ItemCardapio> itens = new ArrayList<>();

    public void adicionarItem(ItemCardapio itemCardapio) {
        itens.add(itemCardapio);
    }

    public ArrayList<ItemCardapio> getItens() {
        return itens;
    }

    public double calcularTotal() {
        double total = 0;

        for (int i = 0; i < itens.size(); i++) {
            ItemCardapio lanche = itens.get(i);
            total += lanche.getPreco();
        }
        return total;
    }

    public void removerItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
            System.out.println("Item removido com sucesso!");
        } else {
            System.out.println("Item inválido.");
        }
    }
}