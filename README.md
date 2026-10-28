# algoritmo-genetico-java

[![CI](https://github.com/WallaksCardoso/algoritmo-genetico-java/actions/workflows/ci.yml/badge.svg)](https://github.com/WallaksCardoso/algoritmo-genetico-java/actions/workflows/ci.yml)

Trabalho da faculdade: um algoritmo genético que escolhe quais produtos colocar em um caminhão baú
para levar o maior valor sem passar de 3 m³. É o problema da mochila, onde testar todas as
combinações fica inviável rápido e a evolução de uma população de cargas chega a boas respostas.

O [resumo expandido](docs/resumo-expandido.pdf) e a [apresentação](docs/apresentacao.pptx) estão em
`docs/`.

Cada carga é um cromossomo de 0 e 1 (um gene por produto). A nota é o valor total, e quem passa do
volume fica com nota 1. Os pais saem de uma roleta proporcional à nota, o crossover corta em um ponto
aleatório e cada gene pode se inverter pela taxa de mutação.

```bash
./mvnw -q exec:java
```

```
##### Melhor solução G -> 4 #####
Valor: 12039.50
Espaço: 2.95
Cromossomo: [1, 0, 1, 0, 1, ...]
```

Os produtos e os parâmetros (população, gerações, taxa de mutação) ficam em `Main.java`. Testes com
`./mvnw verify`.

## Licença

MIT
