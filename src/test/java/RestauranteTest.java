import cardapio.ComCheddar;
import cardapio.ItemCardapio;
import cardapio.Lanche;
import cardapio.LancheFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedido.GerenciadorPedidos;

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
}
