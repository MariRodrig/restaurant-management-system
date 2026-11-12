import cardapio.LancheFactory;
import facade.RestauranteFacade;
import financeiro.Transacao;
import org.junit.jupiter.api.Test;
import pedido.Pedido;
import pedido.StatusPedido;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteFacadeTest {

    RestauranteFacade restauranteFacade = new RestauranteFacade();

    @Test
    void deveFinalizarPedidoERegistrarTransacao() {

        Pedido pedido = restauranteFacade.criarPedido("Maria");
        pedido.adicionarItem(LancheFactory.xBurger());

        restauranteFacade.finalizarPedido(pedido.getIdentificador());

        assertEquals(pedido.getStatusPedido(), StatusPedido.FINALIZADO);

        Transacao transacao = new Transacao(pedido.getIdentificador(), pedido.calcularTotal() + pedido.calcularGorjeta());

        assertTrue(restauranteFacade.existeTransacao(transacao));
    }

    @Test
    void naoDeveFinalizarPedidoInexistente() {

        assertThrows(IllegalArgumentException.class, () -> restauranteFacade.finalizarPedido(999));
    }

    @Test
    void naoDeveFinalizarPedidoDuasVezes(){
        Pedido pedido = restauranteFacade.criarPedido("Maria");

        restauranteFacade.finalizarPedido(pedido.getIdentificador());
        assertThrows(IllegalStateException.class, () -> restauranteFacade.finalizarPedido(pedido.getIdentificador()));
    }

    @Test
    void naoDeveFinalizarPedidoCancelado(){
        Pedido pedido =  restauranteFacade.criarPedido("Maria");
        restauranteFacade.cancelarPedido(pedido.getIdentificador());

        assertThrows(IllegalStateException.class, () -> restauranteFacade.finalizarPedido(pedido.getIdentificador()));
    }
}
