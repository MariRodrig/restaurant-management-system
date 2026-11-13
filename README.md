# Restaurant Management System

Projeto desenvolvido em Java durante o bootcamp, com o objetivo de colocar em prática conceitos de Programação Orientada a Objetos, Collections, tratamento de exceções, padrões de projeto e testes unitários.

O sistema simula o gerenciamento de um restaurante por meio de um menu no console, permitindo registrar, consultar, modificar e cancelar pedidos.

## Funcionalidades

- Cadastro de pedidos com identificação automática e nome do cliente.
- Cardápio com X-Burger, X-Salada e X-Bacon.
- Personalização dos lanches, permitindo retirar ingredientes sem alterar o preço.
- Inclusão de adicionais pagos, como cheddar e bacon extra.
- Inclusão e remoção de itens em pedidos existentes.
- Cancelamento de pedidos, desconsiderando seus valores do total vendido.
- Cálculo do total de cada pedido e do faturamento do dia.
- Contagem das diferentes combinações de lanches vendidos.
- Tratamento de entradas inválidas, permitindo novas tentativas sem interromper o programa.
- Aplicação de descontos percentuais nos pedidos. 
- Validação de descontos entre 0% e 100%. 
- Restrição de descontos em pedidos finalizados ou cancelados. 
- Cálculo da gorjeta sobre o valor após o desconto. 
- Fechamento financeiro considerando os descontos aplicados.

## Organização do projeto

O projeto foi dividido em quatro pacotes:

- **app:** contém `MenuConsole` e `RestauranteFacade`, responsáveis pela interação com o usuário e pela comunicação entre as funcionalidades.
- **cardapio:** contém a interface `ItemCardapio`, a classe `Lanche`, a `LancheFactory` e os Decorators dos adicionais.
- **pedido:** contém `Pedido` e `GerenciadorPedidos`, responsáveis pelos itens, armazenamento e gerenciamento dos pedidos.
- **menu:** contém a classe `Menu`, responsável por iniciar o programa e controlar o fluxo principal.

## Conceitos aplicados

- **POO:** encapsulamento, interfaces, herança e polimorfismo.
- **Factory:** centraliza a criação dos lanches do cardápio.
- **Decorator:** permite acrescentar adicionais aos lanches, atualizando suas descrições e preços.
- **Facade:** centraliza o acesso às operações de gerenciamento do restaurante.
- **Collections:** `ArrayList` para os itens, `HashMap` para armazenar os pedidos e `HashSet` para identificar combinações diferentes.
- **Tratamento de exceções:** utilização de `try-catch`, `InputMismatchException` e validações para evitar entradas inválidas.
- **JUnit 5:** testes para validação de preço, cálculo com adicional e rejeição de nome de cliente nulo.

## Como executar
O projeto utiliza Java 17 e Maven.

### Opção 1 — Pelo IntelliJ IDEA
1. Clone o repositório e abra o projeto no IntelliJ IDEA.
2. Aguarde o carregamento das dependências do Maven.
3. Execute o método main() da classe Menu.
4. Utilize as opções exibidas no console.
### Opção 2 — Pelo arquivo JAR
1. Certifique-se de que o Java 17 e o Maven estejam instalados.
2. Abra o terminal na pasta raiz do projeto.
3. Gere o executável:
   mvn clean package


4. Execute o arquivo gerado:
   java -jar target/restaurant-management-system-1.0-SNAPSHOT.jar
## Demonstração do projeto

O vídeo apresenta as funcionalidades implementadas nesta primeira etapa do bootcamp.

Para melhor visualização do código, recomendamos assistir ao vídeo em 1080p (Full HD).

🎥 [Assistir à demonstração do projeto](https://youtu.be/ElDz3q07NQU)

## Testes

O projeto possui testes automatizados utilizando **JUnit 5**, abrangendo validações, regras de negócio e cálculos financeiros.

Os testes verificam cenários como rejeição de preços negativos, cálculo de lanches com adicionais, validação de nomes de clientes e operações relacionadas aos pedidos.

### Desenvolvimento orientado a testes (TDD)

A funcionalidade de descontos foi desenvolvida utilizando a metodologia **TDD (Test-Driven Development)**, seguindo o ciclo **RED → GREEN → REFACTOR**.

Foram implementados testes para validar:

- Cálculo de descontos percentuais.
- Rejeição de descontos negativos ou superiores a 100%.
- Restrição de descontos em pedidos finalizados ou cancelados.
- Cálculo de gorjetas considerando os descontos.
- Integração dos descontos ao fechamento financeiro.

## Considerações finais

O projeto foi desenvolvido em etapas, começando pela criação dos lanches e pedidos, passando pela implementação dos padrões de projeto e finalizando com a personalização, modificação dos pedidos e tratamento de entradas inválidas.

Durante o desenvolvimento, pude praticar a organização das classes, a separação de responsabilidades e a aplicação dos conceitos estudados no bootcamp.