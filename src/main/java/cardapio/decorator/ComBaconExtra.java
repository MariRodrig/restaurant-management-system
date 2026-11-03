package cardapio.decorator;

import cardapio.ItemCardapio;

public class ComBaconExtra extends ItemCardapioDecorator {

    public ComBaconExtra(ItemCardapio itemCardapio){
        super(itemCardapio);
    }

    @Override
    public String toString() {
        return getDescricao() + " - R$ " + String.format("%.2f", getPreco());
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " + bacon extra";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 6.00;
    }
}
