package app;

import cardapio.*;
import pedido.GerenciadorPedidos;
import pedido.Pedido;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConsole {

    Scanner sc = new Scanner(System.in);
    int opcao = 0;

    GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();

    public void exibirMenu() {
        System.out.println("=== RESTAURANTE === ");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Buscar pedido");
        System.out.println("3 - Listar pedidos do dia");
        System.out.println("4 - Fechamento do dia");
        System.out.println("0 - Sair");
    }

    public void registrarItens(Pedido pedido) {

        int lancheEscolhido = opcaoLanche();

        Lanche lanche = switch (lancheEscolhido) {
            case 1 -> LancheFactory.xBurger();
            case 2 -> LancheFactory.xSalada();
            case 3 -> LancheFactory.xBacon();
            default -> null;
        };

        if (lanche != null) {
            removerIngredientes(lanche);

            ItemCardapio lancheComAdicionais = adicionarAcrescimo(lanche);

            pedido.adicionarItem(lancheComAdicionais);

            System.out.println("Lanche adicionado ao pedido!");
        } else {
            System.out.println("Opção inválida.");
        }

        int novoItem;

        while ((novoItem = addItemPedido()) == 1) {

            int novoLancheEscolhido = opcaoLanche();

            Lanche novoLanche = switch (novoLancheEscolhido) {
                case 1 -> LancheFactory.xBurger();
                case 2 -> LancheFactory.xSalada();
                case 3 -> LancheFactory.xBacon();
                default -> null;
            };

            if (novoLanche != null) {
                removerIngredientes(novoLanche);

                ItemCardapio lancheComAdicionais = adicionarAcrescimo(novoLanche);

                pedido.adicionarItem(novoLanche);

                System.out.println("Novo lanche adicionado!");
            } else {
                System.out.println("Opção inválida.");
            }

            System.out.println("Itens adicionados ao pedido com sucesso!");
        }
    }

    public ItemCardapio adicionarAcrescimo(ItemCardapio itemCardapio) {
        int opcaoAdd = 0;

        while (true) {
            try {
                System.out.println("Deseja adicionar algum acréscimo?");
                System.out.println("1 - SIM | 2 - NÃO");

                opcaoAdd = sc.nextInt();
                sc.nextLine();

                if (opcaoAdd == 1 || opcaoAdd == 2) {
                    break;
                }

                System.out.println("Opção inválida!");

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números!");
                sc.nextLine();
            }
        }

        while (opcaoAdd == 1) {
            int adicionalEscolhido;

            while (true) {
                try {
                    System.out.println("Escolha o adicional:");
                    System.out.println("1 - Cheddar - R$ 4,00");
                    System.out.println("2 - Bacon extra - R$ 6,00");

                    adicionalEscolhido = sc.nextInt();
                    sc.nextLine();

                    if (adicionalEscolhido == 1 || adicionalEscolhido == 2) {
                        break;
                    }

                    System.out.println("Opção inválida!");

                } catch (InputMismatchException e) {
                    System.out.println("Digite apenas números!");
                    sc.nextLine();
                }
            }

            switch (adicionalEscolhido) {
                case 1 -> itemCardapio = new ComCheddar(itemCardapio);
                case 2 -> itemCardapio = new ComBaconExtra(itemCardapio);
            }

            while (true) {
                try {
                    System.out.println("Deseja adicionar outro acréscimo?");
                    System.out.println("1 - SIM | 2 - NÃO");

                    opcaoAdd = sc.nextInt();
                    sc.nextLine();

                    if (opcaoAdd == 1 || opcaoAdd == 2) {
                        break;
                    }

                    System.out.println("Opção inválida!");

                } catch (InputMismatchException e) {
                    System.out.println("Digite apenas números!");
                    sc.nextLine();
                }
            }
        }

        return itemCardapio;
    }

    public int lerOpcao() {
        while (true) {
            try {
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();

                if (opcao >= 0 && opcao <= 4) {
                    return opcao;
                }

                System.out.println("Opção inválida! Escolha de 0 a 4.");

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números! ");
                sc.nextLine();
            }
        }
    }


    public int opcaoLanche() {
        while (true) {
            try {
                System.out.println("=== CARDÁPIO ===");
                System.out.println("1 - X-Burger - R$ 16,00");
                System.out.println("2 - X-Salada - R$ 18,00");
                System.out.println("3 - X-Bacon - R$ 22,00");

                System.out.print("Digite o lanche do cardápio que você deseja: ");
                int opcaoLanche = sc.nextInt();
                sc.nextLine();

                if (opcaoLanche >= 1 && opcaoLanche <= 3) {
                    return opcaoLanche;
                }

                System.out.println("Opção inválida! Escolha de 1 a 3.");

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números!");
                sc.nextLine();
            }
        }
    }

    public String lerNomeCliente() {
        while (true) {
            System.out.print("Digite o nome do cliente: ");

            String nomeCliente = sc.nextLine();

            if (!nomeCliente.isBlank()) {
                return nomeCliente;
            }

            System.out.println("Nome inválido! Digite um nome válido.");
        }
    }

    private void removerIngredientes(Lanche lanche) {
        int ingredienteRemover;

        do {
            ingredienteRemover = escolherIngredienteRemover();

            switch (ingredienteRemover) {
                case 1 -> lanche.setCebola(false);
                case 2 -> lanche.setQueijo(false);
                case 3 -> lanche.setAlface(false);
                case 4 -> lanche.setTomate(false);
                case 0 -> System.out.println("Personalização finalizada.");
                default -> System.out.println("Opção inválida.");
            }

        } while (ingredienteRemover != 0);
    }

    private int escolherIngredienteRemover() {
        while (true) {
            try {
                System.out.println("Deseja retirar algum ingrediente ? Se sim qual ingrediente?");
                System.out.println("1 - Cebola");
                System.out.println("2 - Queijo");
                System.out.println("3 - Alface");
                System.out.println("4 - Tomate");
                System.out.println("0 - Não retirar mais ingredientes");

                int opcao = sc.nextInt();
                sc.nextLine();

                if (opcao >= 0 && opcao <= 4) {
                    return opcao;
                }

                System.out.println("Opção inválida! Escolha de 0 a 4.");

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números!");
                sc.nextLine();
            }
        }
    }

    public int addItemPedido() {
        while (true) {
            try {
                System.out.println("Deseja adicionar outro ítem ao seu pedido? ");
                System.out.println("1 - SIM | 2 - NÃO");

                int opcaoAddItem = sc.nextInt();
                sc.nextLine();

                if (opcaoAddItem == 1 || opcaoAddItem == 2) {
                    return opcaoAddItem;
                }

                System.out.println("Opção inválida! Digite 1 ou 2.");

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números!");
                sc.nextLine();
            }
        }
    }

    public int escolherPedido() {
        while (true) {
            try {
                System.out.println("Digite o número do pedido que deseja modificar:");

                int numeroPedido = sc.nextInt();
                sc.nextLine();

                if (numeroPedido > 0) {
                    return numeroPedido;
                }

                System.out.println("Número inválido! Digite um número maior que zero.");

                return numeroPedido;

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números!");
                sc.nextLine();
            }
        }
    }

    public int opcaoModificarPedido() {
        while (true) {
            try {
                System.out.println("=== MODIFICAR PEDIDO ===");
                System.out.println("1 - Acrescentar item");
                System.out.println("2 - Remover item");
                System.out.println("3 - Cancelar pedido");
                System.out.println("0 - Voltar");

                int opcao = sc.nextInt();
                sc.nextLine();

                if (opcao >= 0 && opcao <= 3) {
                    return opcao;
                }

                System.out.println("Opção inválida! Escolha de 0 a 3.");

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números!");
                sc.nextLine();
            }
        }
    }

    public int escolherItemRemover(Pedido pedido) {
        System.out.println("=== ITENS DO PEDIDO ===");

        if (pedido.getItens().isEmpty()) {
            System.out.println("Este pedido não possui itens para remover.");
            return -1;
        }

        for (int i = 0; i < pedido.getItens().size(); i++) {
            System.out.println((i + 1) + " - " + pedido.getItens().get(i).getDescricao());
        }

        while (true) {
            try {

                System.out.println("Digite o número do item que deseja remover:");

                int itemEscolhido = sc.nextInt();
                sc.nextLine();

                if (itemEscolhido >= 1 && itemEscolhido <= pedido.getItens().size()) {
                    return itemEscolhido;
                }

                System.out.println("Item inválido! Escolha um número da lista.");

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números!");
                sc.nextLine();
            }
        }
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}



