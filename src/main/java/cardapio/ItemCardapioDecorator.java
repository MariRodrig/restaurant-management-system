package cardapio;

public class ItemCardapioDecorator implements  ItemCardapio{

    protected final ItemCardapio itemCardapioEmbrulhado;

    public ItemCardapioDecorator(ItemCardapio lancheEmbrulhado) {
        this.itemCardapioEmbrulhado = lancheEmbrulhado;
    }

    @Override
    public String getDescricao() {
        return itemCardapioEmbrulhado.getDescricao();
    }

    @Override
    public double getPreco() {
        return itemCardapioEmbrulhado.getPreco();
    }
}
