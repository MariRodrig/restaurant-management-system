import cardapio.decorator.ComCheddar;
import cardapio.ItemCardapio;
import cardapio.Lanche;
import cardapio.LancheFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.GerenciadorPedidos;
import pedido.Pedido;
import pedido.StatusPedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestauranteTest {

    private GerenciadorPedidos gerenciadorPedidos;

    @BeforeEach
    void setUp() {
        gerenciadorPedidos = new GerenciadorPedidos();
    }

    @Test
    void deveRecusarPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Lanche("X-Burger", -16.00));
    }

    @Test
    void deveCalcularPrecoComAdicional() {
        ItemCardapio lanche = LancheFactory.xBurger();
        lanche = new ComCheddar(lanche);

        assertEquals(20.00, lanche.getPreco(), 0.001);
    }


    @Test
    void deveLancarExcecaoQuandoNomeClienteNulo() {
        assertThrows(IllegalArgumentException.class, () -> gerenciadorPedidos.criarPedido(null));
    }

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
    void naoDeveCancelarPedidoCancelado(){
        Pedido pedido = new Pedido(1, "Maria");
        pedido.cancelarPedido();

        assertThrows(IllegalStateException.class, () -> pedido.cancelarPedido());
    }

    @Test
    void naoDeveFinalizarPedidoCancelado(){
        Pedido pedido = new Pedido(1, "Maria");
        pedido.cancelarPedido();

        assertThrows(IllegalStateException.class, () -> pedido.finalizarPedido());
    }

    @Test
    void deveCancelarPedidoAberto(){
        Pedido pedido = new Pedido(1, "Maria");
        pedido.cancelarPedido();

        assertEquals(StatusPedido.CANCELADO, pedido.getStatusPedido());
    }

    @Test
    void naoDeveCancelarPedidoFinalizado(){
        Pedido pedido = new Pedido(1, "Maria");
        pedido.finalizarPedido();

        assertThrows(IllegalStateException.class, () -> pedido.cancelarPedido());
    }
}
