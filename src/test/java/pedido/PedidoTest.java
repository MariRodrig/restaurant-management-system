package pedido;

import cardapio.LancheFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PedidoTest {

    @Test
    void deveIniciarPedidoAberto() {
        Pedido pedido = new Pedido(1, "Maria");

        assertEquals(StatusPedido.ABERTO, pedido.getStatusPedido());
    }

    @Test
    void deveFinalizarPedidoAberto() {
        Pedido pedido = new Pedido(1, "Maria");
        pedido.finalizarPedido();

        assertEquals(StatusPedido.FINALIZADO, pedido.getStatusPedido());
    }


    @Test
    void naoDeveFinalizarPedidoFinalizado() {
        Pedido pedido = new Pedido(1, "Maria");
        pedido.finalizarPedido();

        assertThrows(IllegalStateException.class, () -> pedido.finalizarPedido());
    }


    @Test
    void naoDeveCancelarPedidoCancelado() {
        Pedido pedido = new Pedido(1, "Maria");
        pedido.cancelarPedido();

        assertThrows(IllegalStateException.class, () -> pedido.cancelarPedido());
    }

    @Test
    void naoDeveFinalizarPedidoCancelado() {
        Pedido pedido = new Pedido(1, "Maria");
        pedido.cancelarPedido();

        assertThrows(IllegalStateException.class, () -> pedido.finalizarPedido());
    }

    @Test
    void deveCancelarPedidoAberto() {
        Pedido pedido = new Pedido(1, "Maria");
        pedido.cancelarPedido();

        assertEquals(StatusPedido.CANCELADO, pedido.getStatusPedido());
    }

    @Test
    void naoDeveCancelarPedidoFinalizado() {
        Pedido pedido = new Pedido(1, "Maria");
        pedido.finalizarPedido();

        assertThrows(IllegalStateException.class, () -> pedido.cancelarPedido());
    }

    @Test
    void deveCalcularGorjetaDeDezPorCento(){
        Pedido pedido = new Pedido(1, "Maria");

        pedido.adicionarItem(LancheFactory.xBurger());
        pedido.setPercentualGorjeta(10.0);

        double gorjetaEsperada = pedido.calcularTotal() * 0.10;

        assertEquals(gorjetaEsperada, pedido.calcularGorjeta(), 0.001);
    }

    @Test
    void deveCalcularGorjetaPersonalizada(){
        Pedido pedido = new Pedido(1, "Maria");

        pedido.adicionarItem(LancheFactory.xBurger());
        pedido.setPercentualGorjeta(15.0);

        assertEquals(2.4, pedido.calcularGorjeta(), 0.001);
    }

    @Test
    void naoDeveAceitarGorjetaNegativa(){
        Pedido pedido = new Pedido(1, "Maria");

        assertThrows(IllegalArgumentException.class, () -> pedido.setPercentualGorjeta(-10.0));
    }

    @Test
    void naoDeveAlterarGorjetaDePedidoFinalizado(){
        Pedido pedido = new Pedido(1, "Maria");

        pedido.finalizarPedido();
        assertThrows(IllegalStateException.class, () -> pedido.setPercentualGorjeta(5.0));
    }

    @Test
    void naoDeveAlterarGorjetaDePedidoCancelado(){
        Pedido pedido = new Pedido(1, "Maria");

        pedido.cancelarPedido();
        assertThrows(IllegalStateException.class, () -> pedido.setPercentualGorjeta(5.0));
    }

}
