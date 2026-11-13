package pedido;

import cardapio.ItemCardapio;

import java.util.ArrayList;

public class Pedido {

    private int identificador;
    private String nome;
    private StatusPedido statusPedido;
    private double percentualGorjeta;
    private double percentualDesconto;

    public Pedido(int identificador, String nome) {
        this.identificador = identificador;
        this.setNome(nome);
        this.statusPedido = StatusPedido.ABERTO;
    }

    @Override
    public String toString() {
        return "Pedido nº " + identificador +
                "\nCliente: " + nome +
                "\nItens: " + itens +
                "\nTotal: R$ " + calcularTotal() +
                "\nStatus: " + statusPedido;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
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

    public void setPercentualGorjeta(double percentualGorjeta) {

        if (statusPedido != StatusPedido.ABERTO) {
            throw new IllegalStateException(
                    "Não é possível alterar a gorjeta de um pedido finalizado ou cancelado."
            );
        }

        if (percentualGorjeta < 0) {
            throw new IllegalArgumentException("O percentual não pode ser negativo.");
        }
        this.percentualGorjeta = percentualGorjeta;
    }

    private ArrayList<ItemCardapio> itens = new ArrayList<>();

    public void adicionarItem(ItemCardapio itemCardapio) {
        if (statusPedido != StatusPedido.ABERTO) {
            throw new IllegalStateException("Não é possível adicionar itens a este pedido.");
        }
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

    public double calcularGorjeta() {

        return (calcularTotalComDesconto() * percentualGorjeta) / 100;
    }


    public void removerItem(int indice) {
        if (statusPedido != StatusPedido.ABERTO) {
            throw new IllegalStateException("Não é possível remover itens deste pedido.");
        }
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
            System.out.println("Item removido com sucesso!");
        } else {
            System.out.println("Item inválido.");
        }
    }

    public void finalizarPedido() {
        if (statusPedido == StatusPedido.FINALIZADO) {
            throw new IllegalStateException("Pedido já foi finalizado anteriormente.");
        }
        if (statusPedido == StatusPedido.CANCELADO) {
            throw new IllegalStateException("Não é possível finalizar um pedido cancelado.");
        }
        this.statusPedido = StatusPedido.FINALIZADO;
    }

    public void cancelarPedido() {
        if (statusPedido == StatusPedido.FINALIZADO) {
            throw new IllegalStateException("Pedido já foi finalizado, não é possível cancelar.");
        }
        if (statusPedido == StatusPedido.CANCELADO) {
            throw new IllegalStateException("Pedido já foi cancelado.");
        }
        this.statusPedido = StatusPedido.CANCELADO;
    }

    public void aplicarDesconto(double percentual) {
        if (percentual < 0 || percentual > 100){
            throw new IllegalArgumentException("Percentual de desconto inválido!");
        }

        if (statusPedido != StatusPedido.ABERTO){
            throw new IllegalStateException("Não é possível aplicar desconto em um pedido fechado");
        }

        this.percentualDesconto = percentual;
    }

    public double calcularTotalComDesconto() {

        double total = calcularTotal();
        double valorDesconto = (calcularTotal() * percentualDesconto) / 100;

        return total - valorDesconto;
    }
}
