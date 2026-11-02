package pedido;

import cardapio.ItemCardapio;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GerenciadorPedidos {

    private final HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Pedido criarPedido(String nomeCliente) {

        if (nomeCliente == null || nomeCliente.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente inválido.");
        }

        Pedido pedido = new Pedido(contadorPedidos, nomeCliente);
        pedidos.put(contadorPedidos, pedido);
        contadorPedidos++;
        return pedido;
    }


    public Pedido buscarPedido(int numero) {
        return pedidos.get(numero);
    }

    public double calcularTotalDoDia() {
        double totalDoDia = 0;

        for (Pedido pedido : pedidos.values()) {
            totalDoDia += pedido.calcularTotal();
        }
        return totalDoDia;

    }

    public void listarPedidos() {
        for (Pedido pedido : pedidos.values()) {
            System.out.println(pedido);
        }
    }

    public boolean cancelarPedido(int numeroPedido) {
        if (pedidos.containsKey(numeroPedido)) {
            pedidos.remove(numeroPedido);
            return true;
        }
        return false;
    }

    public void listarCombinacoes() {
        Set<String> combinacoes = new HashSet<>();

        for (Pedido pedido : pedidos.values()) {
            for (ItemCardapio lanche : pedido.getItens()) {
                combinacoes.add(lanche.getDescricao());
            }
        }

        System.out.println("=== COMBINAÇÕES VENDIDAS ===");

        for (String combinacao : combinacoes) {
            System.out.println(combinacao);
        }

        System.out.println("Total de combinações diferentes: " + combinacoes.size());
    }
}