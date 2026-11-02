package cardapio;

public class ComCheddar extends ItemCardapioDecorator{

    public ComCheddar(ItemCardapio itemCardapio){
        super(itemCardapio);
    }

    @Override
    public String toString() {
        return getDescricao() + " - R$ " + String.format("%.2f", getPreco());
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + cheddar";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 4.00;
    }
}
