import financeiro.Transacao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TransacaoTest {

    @Test
    void deveConsiderarTransacoesIguaisQuandoNumeroPedidoForIgual() {
        Transacao transacao1 = new Transacao(1, 50);
        Transacao transacao2 = new Transacao(1, 85);

        assertEquals(transacao1, transacao2);
    }

    @Test
    void deveConsiderarTransacoesDiferentesQuandoNumeroPedidoForDiferente() {
        Transacao transacao1 = new Transacao(1, 50);
        Transacao transacao2 = new Transacao(2, 85);

        assertNotEquals(transacao1, transacao2);
    }

    @Test
    void deveGerarMesmoHashCodeParaTransacoesIguais(){
        Transacao transacao1 = new Transacao(1, 50);
        Transacao transacao2 = new Transacao(1, 85);

        assertEquals(transacao1.hashCode(), transacao2.hashCode());
    }
}
