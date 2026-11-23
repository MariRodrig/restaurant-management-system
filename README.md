# Restaurant Management System

Sistema de gerenciamento de restaurante desenvolvido em **Java 17**, inicialmente como projeto de um bootcamp e aprimorado posteriormente.

A aplicação funciona pelo console e permite cadastrar pedidos, personalizar lanches, aplicar descontos, adicionar gorjetas e realizar o fechamento financeiro do dia.

O objetivo foi praticar Programação Orientada a Objetos, Collections, padrões de projeto, tratamento de exceções e testes unitários.

## Funcionalidades

- Cadastro, consulta e cancelamento de pedidos.
- Cardápio com X-Burger, X-Salada e X-Bacon.
- Personalização de ingredientes e adicionais pagos.
- Inclusão e remoção de itens.
- Controle de pedidos com Enum: ABERTO, FINALIZADO e CANCELADO.
- Aplicação de descontos percentuais.
- Cálculo de gorjetas sobre o valor com desconto.
- Fechamento do dia com total de vendas e gorjetas.
- Validação de entradas e regras de negócio.

## Tecnologias e conceitos

- **Java 17 e POO:** classes, interfaces, encapsulamento e polimorfismo.
- **Maven:** gerenciamento do projeto e geração do JAR.
- **Collections:** ArrayList, HashMap e HashSet.
- **Factory:** centraliza a criação dos lanches.
- **Decorator:** permite adicionar extras aos lanches.
- **Facade:** simplifica o acesso às operações do restaurante.
- **Enum:** controla os status dos pedidos.
- **Exceções personalizadas:** tratamento de erros relacionados aos pedidos.
- **Extract Method:** refatoração para organizar a classe Menu.
- **JUnit 5:** testes unitários das funcionalidades e regras de negócio.

## Estrutura do projeto

O código está organizado nos seguintes pacotes:

- `cardapio`: lanches, ingredientes e adicionais.
- `pedido`: pedidos e regras de gerenciamento.
- `financeiro`: caixa e transações.
- `facade`: acesso às operações do restaurante.
- `console`: menu e interação com o usuário.
- `exception`: exceções personalizadas.

## Como executar

**Requisito:** Java 17 ou superior.

### Pelo IntelliJ IDEA

1. Clone o repositório.
2. Abra o projeto no IntelliJ.
3. Execute o método `main()` da classe `Menu`.

### Pelo JAR executável

1. Acesse a seção [Releases](../../releases/latest).
2. Baixe o arquivo `restaurant-management-system-1.1.1.jar`.
3. Abra o terminal na pasta do arquivo e execute:

```bash
java -jar restaurant-management-system-1.1.1.jar
```

## Demonstração

Neste vídeo, apresento a estrutura do projeto, os principais conceitos utilizados e o funcionamento do sistema.

🎥 [Assistir à demonstração no YouTube](https://youtu.be/XWoI5oHyg3E)

## Testes

O projeto utiliza **JUnit 5** para testar cálculos, validações, status dos pedidos e exceções.

A funcionalidade de descontos também foi desenvolvida utilizando **TDD**, seguindo o ciclo RED → GREEN → REFACTOR.

## Próximos passos

A próxima etapa será evoluir o projeto para uma **API REST com Spring Boot, JPA e PostgreSQL**, aproveitando as regras de negócio já implementadas.
