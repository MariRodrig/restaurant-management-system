package financeiro;

import pedido.GerenciadorPedidos;
import pedido.Pedido;
import pedido.StatusPedido;

import java.util.HashSet;
import java.util.Set;

public class Caixa {

    private final GerenciadorPedidos gerenciadorPedidos;

    private final Set<Transacao> transacoes = new HashSet<>();

    public Caixa(GerenciadorPedidos gerenciadorPedidos) {
        this.gerenciadorPedidos = gerenciadorPedidos;
    }

    public double calcularTotalDoDia() {
        double totalDoDia = 0;

        for (Pedido pedido : gerenciadorPedidos.getPedidos().values()) {
            if (pedido.getStatusPedido() == StatusPedido.FINALIZADO) {
                totalDoDia += pedido.calcularTotal();
            }
        }
        return totalDoDia;

    }

    public double calcularTotalGorjetas() {
        double totalGorjetas = 0;

        for (Pedido pedido : gerenciadorPedidos.getPedidos().values()) {
            if (pedido.getStatusPedido() == StatusPedido.FINALIZADO) {
                totalGorjetas += pedido.calcularGorjeta();
            }
        }

        return totalGorjetas;
    }

    public boolean registrarTransacao(Transacao transacao) {
        boolean foiAdicionada = transacoes.add(transacao);

        if (!foiAdicionada) {
            System.out.println("Esse pedido já existe!");
        }
        return foiAdicionada;
    }

    public boolean existeTransacao(Transacao transacao) {
        return transacoes.contains(transacao);
    }

}
