package pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GerenciadorPedidosTest {

    private GerenciadorPedidos gerenciadorPedidos;

    @BeforeEach
    void setUp() {
        gerenciadorPedidos = new GerenciadorPedidos();
    }

    @Test
    void deveLancarExcecaoQuandoNomeClienteNulo() {
        assertThrows(IllegalArgumentException.class, () -> gerenciadorPedidos.criarPedido(null));
    }

}
