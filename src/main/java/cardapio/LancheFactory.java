package cardapio;


public class LancheFactory {

    public static Lanche xBurger() {
        return new Lanche( "X-Burger", 16.00, "pão, hambúrguer, queijo, alface, tomate");
    }

    public static Lanche xSalada() {
         return new Lanche( "X-Salada", 18.00, "pão, hambúrguer, queijo, alface, tomate, cebola");
    }

    public static Lanche xBacon() {
        return new Lanche("X-Bacon", 22.00, "pão, hambúrguer, queijo, bacon, alface, tomate, cebola");
    }
}

