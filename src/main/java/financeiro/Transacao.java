package financeiro;

public class Transacao {

    private final int numeroDoPedido;
    private final double valorTransacao;


    public Transacao(int numeroDoPedido, double valorTransacao) {
        this.numeroDoPedido = numeroDoPedido;
        this.valorTransacao = valorTransacao;
    }

    public int getNumeroDoPedido() {
        return numeroDoPedido;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Transacao)){
            return false;
        }

        Transacao outraTransacao = (Transacao) obj;
        return this.numeroDoPedido == outraTransacao.getNumeroDoPedido();
    }

    @Override
    public int hashCode() {
        return numeroDoPedido;
    }
}