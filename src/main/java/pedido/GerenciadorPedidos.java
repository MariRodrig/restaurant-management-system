package pedido;

import cardapio.ItemCardapio;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GerenciadorPedidos {

    private final HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Map<Integer, Pedido> getPedidos() {
        return pedidos;
    }

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

    public void listarPedidos() {
        boolean isPedidoEncontrado = false;

        for (Pedido pedido : pedidos.values()) {
            if (pedido.getStatusPedido() != StatusPedido.CANCELADO) {
                System.out.println(pedido);
                isPedidoEncontrado = true;
            }
        }
        if (!isPedidoEncontrado) {
            System.out.println("Nenhum pedido disponível.");
        }
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

    public Pedido finalizarPedido(int numeroPedido) {

        Pedido pedido = pedidos.get(numeroPedido);

        if (pedido != null) {
            pedido.finalizarPedido();
        } else {
            throw new IllegalArgumentException("Pedido não encontrado");
        }

        return pedido;
    }


    public Pedido cancelarPedido(int numeroPedido) {

        Pedido pedido = pedidos.get(numeroPedido);

        if (pedido != null) {
            pedido.cancelarPedido();
        } else {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        return pedido;
    }

}