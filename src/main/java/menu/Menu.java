package menu;

import app.MenuConsole;
import app.RestauranteFacade;
import cardapio.Lanche;
import pedido.GerenciadorPedidos;
import pedido.Pedido;

public class Menu {
    public static void main(String[] args) {

        MenuConsole menuConsole = new MenuConsole();
        RestauranteFacade restauranteFacade = new RestauranteFacade();

        int opcao;

        do {
            menuConsole.exibirMenu();
            opcao = menuConsole.lerOpcao();

            switch (opcao) {
                case 1 -> {
                    String nomeCliente = menuConsole.lerNomeCliente();

                    Pedido pedido = restauranteFacade.criarPedido(nomeCliente);

                    menuConsole.registrarItens(pedido);
                }

                case 2 -> {
                    restauranteFacade.listarPedidos();

                    int numeroPedido = menuConsole.escolherPedido();

                    Pedido pedidoEncontrado = restauranteFacade.buscarPedido(numeroPedido);

                    if (pedidoEncontrado != null) {
                        menuConsole.exibirMensagem("Pedido encontrado!");

                        int opcaoModificar = menuConsole.opcaoModificarPedido();

                        switch (opcaoModificar) {
                            case 1 -> menuConsole.registrarItens(pedidoEncontrado);
                            case 2 -> {
                                int itemEscolhido = menuConsole.escolherItemRemover(pedidoEncontrado);

                                if (itemEscolhido != -1) {
                                    pedidoEncontrado.removerItem(itemEscolhido - 1);
                                }
                            }
                            case 3 -> {
                                boolean cancelado = restauranteFacade.cancelarPedido(numeroPedido);

                                if (cancelado) {
                                    menuConsole.exibirMensagem("Pedido cancelado com sucesso!");
                                } else {
                                    menuConsole.exibirMensagem("Não foi possível cancelar o pedido.");
                                }
                            }
                            case 0 -> menuConsole.exibirMensagem("Voltar");
                            default -> menuConsole.exibirMensagem("Opção inválida.");
                        }

                    } else {
                        menuConsole.exibirMensagem("Pedido não encontrado.");
                    }
                }

                case 3 -> restauranteFacade.listarPedidos();

                case 4 -> {
                    double totalDoDia = restauranteFacade.calcularTotalDoDia();

                    menuConsole.exibirMensagem("Total vendido no dia: R$ " + totalDoDia);

                    restauranteFacade.listarCombinacoes();
                }

                case 0 -> menuConsole.exibirMensagem("Programa finalizado.");
                default -> menuConsole.exibirMensagem("Opção inválida. Digite uma das opções válidas.");
            }
        } while (opcao != 0);

    }
}
