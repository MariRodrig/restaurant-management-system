package console;

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

                        menuConsole.exibirMensagem("Pedido selecionado:");
                        menuConsole.exibirMensagem(pedidoEncontrado.toString());

                        if (pedidoEncontrado.getStatusPedido() == StatusPedido.ABERTO) {

                            int opcaoModificar = menuConsole.opcaoModificarPedido();

                            try {
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

                            } catch (IllegalStateException e) {
                                menuConsole.exibirMensagem(e.getMessage());
                            }

                        } else {
                            menuConsole.exibirMensagem("Este pedido não pode ser modificado.");
                        }

                    } else {
                        menuConsole.exibirMensagem("Pedido não encontrado.");
                    }
                }

                case 3 -> restauranteFacade.listarPedidos();

                case 4 -> {
                    int numeroPedido = menuConsole.escolherPedido();
                    Pedido pedidoEncontrado = restauranteFacade.buscarPedido(numeroPedido);

                    if (pedidoEncontrado != null) {

                        if (pedidoEncontrado.getStatusPedido() == StatusPedido.ABERTO) {

                            try {

                                double percentualGorjeta = menuConsole.escolherGorjeta();
                                pedidoEncontrado.setPercentualGorjeta(percentualGorjeta);

                                double valorGorjeta = pedidoEncontrado.calcularGorjeta();

                                menuConsole.exibirMensagem("Valor da gorjeta: R$ " + valorGorjeta);

                                double totalComGorjeta = pedidoEncontrado.calcularTotal() + valorGorjeta;

                                menuConsole.exibirMensagem("Total com gorjeta: R$ " + totalComGorjeta);

                                restauranteFacade.finalizarPedido(numeroPedido);
                                menuConsole.exibirMensagem("Pedido concluído com sucesso!");

                            } catch (IllegalArgumentException | IllegalStateException e) {
                                menuConsole.exibirMensagem(e.getMessage());
                            }

                        } else {
                            menuConsole.exibirMensagem("Este pedido não pode ser concluído.");
                        }

                        } else{
                            menuConsole.exibirMensagem("Pedido não encontrado.");
                        }
                    }

                    case 5 -> {
                        int numeroPedido = menuConsole.escolherPedido();

                        try {
                            restauranteFacade.cancelarPedido(numeroPedido);
                            menuConsole.exibirMensagem("Pedido cancelado com sucesso!");

                        } catch (IllegalArgumentException | IllegalStateException e) {
                            menuConsole.exibirMensagem(e.getMessage());
                        }
                    }

                    case 6 -> {
                        double totalDoDia = restauranteFacade.calcularTotalDoDia();
                        double totalGorjetas = restauranteFacade.calcularTotalGorjetas();

                        double faturamentoTotal = totalDoDia + totalGorjetas;

                        menuConsole.exibirMensagem("Total vendido no dia: R$ " + totalDoDia);
                        menuConsole.exibirMensagem("Total de gorjetas: R$ " + totalGorjetas);
                        menuConsole.exibirMensagem("Faturamento total: R$ " + faturamentoTotal);

                    }

                    case 7 -> {
                        int numeroPedido = menuConsole.escolherPedido();

                        try {
                            double percentual = menuConsole.escolherDesconto();
                            Pedido pedido = restauranteFacade.buscarPedido(numeroPedido);

                            if (pedido == null) {
                                menuConsole.exibirMensagem("Pedido não encontrado.");
                            } else {
                                pedido.aplicarDesconto(percentual);

                                menuConsole.exibirMensagem(
                                        "Total com desconto: R$ " + pedido.calcularTotalComDesconto()
                                );
                            }
                        }catch  (IllegalArgumentException | IllegalStateException e) {
                            menuConsole.exibirMensagem(e.getMessage());
                        }
                    }
                    case 0 -> menuConsole.exibirMensagem("Programa finalizado.");
                    default -> menuConsole.exibirMensagem("Opção inválida. Digite uma das opções válidas.");
                }
            }
            while (opcao != 0) ;

        }
    }
