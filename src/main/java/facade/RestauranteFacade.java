package facade;

import financeiro.Caixa;
import financeiro.Transacao;
import pedido.GerenciadorPedidos;
import pedido.Pedido;


public class RestauranteFacade {

    private final GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();
    private final Caixa caixa = new Caixa(gerenciadorPedidos);


    public void listarPedidos() {
        gerenciadorPedidos.listarPedidos();
    }

    public Pedido criarPedido(String nomeCliente) {
        return gerenciadorPedidos.criarPedido(nomeCliente);
    }

    public double calcularTotalDoDia() {
        return caixa.calcularTotalDoDia();

    }

    public Pedido buscarPedido(int numeroPedido) {
        return gerenciadorPedidos.buscarPedido(numeroPedido);
    }

    public void cancelarPedido(int numeroPedido) {
        gerenciadorPedidos.cancelarPedido(numeroPedido);
    }

    public void finalizarPedido(int numeroPedido) {
        Pedido pedido = gerenciadorPedidos.buscarPedido(numeroPedido);

        Transacao transacao = new Transacao(numeroPedido, pedido.calcularTotalComDesconto() + pedido.calcularGorjeta());

        if (caixa.existeTransacao(transacao)) {
            throw new IllegalStateException("Transação já registrada para este pedido!");
        }

        gerenciadorPedidos.finalizarPedido(numeroPedido);

        boolean foiRegistrada = caixa.registrarTransacao(transacao);

        if (!foiRegistrada) {
            throw new IllegalStateException("Transação já registrada para este pedido!");
        }
    }

    public double calcularTotalGorjetas() {
        return caixa.calcularTotalGorjetas();
    }

    public boolean existeTransacao(Transacao transacao) {
        return caixa.existeTransacao(transacao);
    }

}