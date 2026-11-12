package cardapio;

import cardapio.decorator.ComCheddar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LancheDecoratorTest {

    @Test
    void deveCalcularPrecoComAdicional() {
        ItemCardapio lanche = LancheFactory.xBurger();
        lanche = new ComCheddar(lanche);

        assertEquals(20.00, lanche.getPreco(), 0.001);
    }

}
