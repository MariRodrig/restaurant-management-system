package cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LancheTest {

    @Test
    void deveRecusarPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Lanche("X-Burger", -16.00));
    }

}
