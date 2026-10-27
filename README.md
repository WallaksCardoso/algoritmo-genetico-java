# Algoritmo Genético em Java

Algoritmo genético que escolhe quais produtos carregar em um caminhão baú para levar o maior valor
possível sem passar do espaço disponível.

[![CI](https://github.com/WallaksCardoso/algoritmo-genetico-java/actions/workflows/ci.yml/badge.svg)](https://github.com/WallaksCardoso/algoritmo-genetico-java/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java-21-informational)
[![Licença](https://img.shields.io/badge/licen%C3%A7a-MIT-informational)](LICENSE)

## Sobre

É uma variação do problema da mochila: cada produto tem um valor e ocupa um volume (m³), e o
caminhão tem um limite de volume. Testar todas as combinações cresce exponencialmente com o número
de produtos; o algoritmo genético encontra boas soluções evoluindo uma população de cargas possíveis.

O projeto acompanha um trabalho acadêmico: o [resumo expandido](docs/resumo-expandido.pdf) e a
[apresentação](docs/apresentacao.pptx) estão em `docs/`.

## Como funciona

- **Cromossomo**: uma lista de genes `0` ou `1`, um por produto (`1` = vai no caminhão).
- **Avaliação**: a nota é a soma dos valores escolhidos; se o volume passa do limite, a nota cai
  para 1, o que tira a solução da disputa.
- **Seleção**: roleta viciada, em que a chance de ser pai é proporcional à nota.
- **Crossover**: um ponto de corte aleatório troca as metades de dois pais e gera dois filhos.
- **Mutação**: cada gene pode se inverter conforme a taxa de mutação.
- **Evolução**: a cada geração o melhor indivíduo é exibido e a melhor solução geral é guardada.

## Stack

- Java 21
- Maven (com Maven Wrapper)
- Lombok
- JUnit 5
- GitHub Actions

## Como rodar

Pré-requisito: Java 21.

```bash
./mvnw -q exec:java
```

O exemplo em `Main.java` usa 14 produtos (geladeiras, TVs, notebooks e outros), limite de 3 m³,
população de 3 indivíduos, 4 gerações e taxa de mutação de 5%. Saída:

```
G: 0
Valor: 9910.91
Espaço: 2.99
Cromossomo: [1, 0, 1, 0, 0, ...]

...

##### Melhor solução G -> 4 #####
Valor: 12039.50
Espaço: 2.95
Cromossomo: [1, 0, 1, 0, 1, ...]

Nome: Geladeira Dako
Nome: TV 55'
...
```

## Testes

```bash
./mvnw verify
```

Cobrem a avaliação (soma de valor e volume), a penalidade de quem passa do limite, o crossover, a
mutação nos casos extremos (taxa 0 e taxa 1) e a execução completa. A CI roda os testes e o exemplo a
cada push.

## Estrutura

```
src/main/java/com/wallaks/algoritmo_genetico_java/
  Main.java                 produtos de exemplo e parâmetros
  AlgoritmoGenetico.java    população, seleção e evolução por gerações
  Individuo.java            cromossomo, avaliação, crossover e mutação
  Produto.java              nome, volume e valor
docs/                       resumo expandido e apresentação do trabalho
```

## Licença

[MIT](LICENSE)
