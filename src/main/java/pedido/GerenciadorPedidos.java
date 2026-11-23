package pedido;

import exception.PedidoNaoEncontradoException;

import java.util.HashMap;
import java.util.Map;

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
        Pedido pedido = pedidos.get(numero);

        if (pedido == null) {
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }

        return pedido;
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

    public void finalizarPedido(int numeroPedido) {

        Pedido pedido = buscarPedido(numeroPedido);

        pedido.finalizarPedido();
    }


    public void cancelarPedido(int numeroPedido) {

        Pedido pedido = buscarPedido(numeroPedido);

        pedido.cancelarPedido();
    }
}
