import financeiro.Caixa;
import financeiro.Transacao;
import org.junit.jupiter.api.Test;
import pedido.GerenciadorPedidos;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CaixaTest {

    GerenciadorPedidos gerenciador = new GerenciadorPedidos();
    Caixa caixa = new Caixa(gerenciador);

    @Test
    void deveRegistrarTransacao() {
        Transacao transacao = new Transacao(1, 50);
        boolean foiRegistrada = caixa.registrarTransacao(transacao);

        assertTrue(foiRegistrada);
    }

    @Test
    void naoDeveRegistrarTransacaoDuplicada() {
        Transacao transacao1 = new Transacao(1, 50);
        Transacao transacao2 = new Transacao(1, 85);

        caixa.registrarTransacao(transacao1);

        boolean foiRegistrada = caixa.registrarTransacao(transacao2);

        assertFalse(foiRegistrada);
    }

    @Test
    void deveIdentificarTransacaoExistente() {
        Transacao transacao = new Transacao(1, 50);
        caixa.registrarTransacao(transacao);

        boolean existe = caixa.existeTransacao(transacao);
        assertTrue(existe);
    }

    @Test
    void deveRetornarFalseParaTransacaoInexistente(){
        Transacao transacao = new Transacao(1, 50);

        assertFalse(caixa.existeTransacao(transacao));
    }
}
