package app;

import pedido.GerenciadorPedidos;
import pedido.Pedido;


public class RestauranteFacade {

    private final GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();

    public void listarPedidos() {
        gerenciadorPedidos.listarPedidos();
    }

    public Pedido criarPedido(String nomeCliente) {
        return gerenciadorPedidos.criarPedido(nomeCliente);
    }

    public double calcularTotalDoDia() {
        return gerenciadorPedidos.calcularTotalDoDia();

    }

    public Pedido buscarPedido(int numeroPedido) {
        return gerenciadorPedidos.buscarPedido(numeroPedido);
    }

    public boolean cancelarPedido(int numeroPedido) {
        return gerenciadorPedidos.cancelarPedido(numeroPedido);
    }

    public void listarCombinacoes() {
        gerenciadorPedidos.listarCombinacoes();
    }
}