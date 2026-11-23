package console;

import exception.RestauranteException;
import facade.RestauranteFacade;
import pedido.Pedido;
import pedido.StatusPedido;

public class Menu {
    public static void main(String[] args) {

        MenuConsole menuConsole = new MenuConsole();
        RestauranteFacade restauranteFacade = new RestauranteFacade();

        int opcao;

        do {
            menuConsole.exibirMenu();
            opcao = menuConsole.lerOpcao();

            switch (opcao) {
                case 1 -> registrarPedido(menuConsole, restauranteFacade);

                case 2 -> consultarPedido(restauranteFacade, menuConsole);

                case 3 -> restauranteFacade.listarPedidos();

                case 4 -> finalizarPedido(menuConsole, restauranteFacade);

                case 5 -> cancelarPedido(menuConsole, restauranteFacade);

                case 6 -> fecharCaixa(restauranteFacade, menuConsole);

                case 7 -> aplicarDesconto(menuConsole, restauranteFacade);

                case 0 -> menuConsole.exibirMensagem("Programa finalizado.");
                default -> menuConsole.exibirMensagem("Opção inválida. Digite uma das opções válidas.");
            }
        }
        while (opcao != 0);

    }

    private static void registrarPedido(MenuConsole menuConsole, RestauranteFacade restauranteFacade) {
        String nomeCliente = menuConsole.lerNomeCliente();

        Pedido pedido = restauranteFacade.criarPedido(nomeCliente);

        menuConsole.registrarItens(pedido);
    }

    private static void consultarPedido(RestauranteFacade restauranteFacade, MenuConsole menuConsole) {
        restauranteFacade.listarPedidos();

        int numeroPedido = menuConsole.escolherPedido("consultar ou modificar");

        try {
            Pedido pedidoEncontrado = restauranteFacade.buscarPedido(numeroPedido);

            menuConsole.exibirMensagem("Pedido selecionado:");
            menuConsole.exibirMensagem(pedidoEncontrado.toString());

            if (pedidoEncontrado.getStatusPedido() == StatusPedido.ABERTO) {

                int opcaoModificar = menuConsole.opcaoModificarPedido();

                switch (opcaoModificar) {

                    case 1 -> menuConsole.registrarItens(pedidoEncontrado);

                    case 2 -> {
                        int itemEscolhido = menuConsole.escolherItemRemover(pedidoEncontrado);

                        if (itemEscolhido != -1) {
                            pedidoEncontrado.removerItem(itemEscolhido - 1);
                        }
                    }

                    case 0 -> menuConsole.exibirMensagem("Voltar");

                    default -> menuConsole.exibirMensagem("Opção inválida.");
                }

            } else {
                menuConsole.exibirMensagem("Este pedido não pode ser modificado.");
            }

        } catch (RestauranteException | IllegalStateException e) {
            menuConsole.exibirMensagem(e.getMessage());
        }
    }

    private static void finalizarPedido(MenuConsole menuConsole, RestauranteFacade restauranteFacade) {
        int numeroPedido = menuConsole.escolherPedido("finalizar");

        try {
            Pedido pedidoEncontrado = restauranteFacade.buscarPedido(numeroPedido);

            if (pedidoEncontrado.getStatusPedido() == StatusPedido.ABERTO) {

                double percentualGorjeta = menuConsole.escolherGorjeta();
                pedidoEncontrado.setPercentualGorjeta(percentualGorjeta);

                double valorGorjeta = pedidoEncontrado.calcularGorjeta();
                menuConsole.exibirMensagem("Valor da gorjeta: R$ " + valorGorjeta);

                double totalComGorjeta = pedidoEncontrado.calcularTotalComDesconto() + valorGorjeta;
                menuConsole.exibirMensagem("Total com gorjeta: R$ " + totalComGorjeta);

                restauranteFacade.finalizarPedido(numeroPedido);
                menuConsole.exibirMensagem("Pedido concluído com sucesso!");

            } else {
                menuConsole.exibirMensagem("Este pedido não pode ser concluído.");
            }

        } catch (RestauranteException | IllegalArgumentException | IllegalStateException e) {
            menuConsole.exibirMensagem(e.getMessage());
        }
    }

    private static void cancelarPedido(MenuConsole menuConsole, RestauranteFacade restauranteFacade) {
        int numeroPedido = menuConsole.escolherPedido("cancelar");

        try {
            restauranteFacade.cancelarPedido(numeroPedido);
            menuConsole.exibirMensagem("Pedido cancelado com sucesso!");

        } catch (RestauranteException | IllegalArgumentException | IllegalStateException e) {
            menuConsole.exibirMensagem(e.getMessage());
        }
    }

    private static void fecharCaixa(RestauranteFacade restauranteFacade, MenuConsole menuConsole) {
        double totalDoDia = restauranteFacade.calcularTotalDoDia();
        double totalGorjetas = restauranteFacade.calcularTotalGorjetas();

        double faturamentoTotal = totalDoDia + totalGorjetas;

        menuConsole.exibirMensagem("Total vendido no dia: R$ " + totalDoDia);
        menuConsole.exibirMensagem("Total de gorjetas: R$ " + totalGorjetas);
        menuConsole.exibirMensagem("Faturamento total: R$ " + faturamentoTotal);
    }

    private static void aplicarDesconto(MenuConsole menuConsole, RestauranteFacade restauranteFacade) {
        int numeroPedido = menuConsole.escolherPedido("selecionar para aplicar desconto");

        try {
            Pedido pedido = restauranteFacade.buscarPedido(numeroPedido);

            double percentual = menuConsole.escolherDesconto();
            pedido.aplicarDesconto(percentual);

            menuConsole.exibirMensagem("Total com desconto: R$ " + pedido.calcularTotalComDesconto());

        } catch (RestauranteException | IllegalArgumentException | IllegalStateException e) {
            menuConsole.exibirMensagem(e.getMessage());
        }
    }
}
