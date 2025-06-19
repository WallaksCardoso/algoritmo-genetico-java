# 🧬 Algoritmo Genético em Java

Projeto de simulação de um **algoritmo genético** para resolver problemas de otimização com restrição de espaço (semelhante ao problema da mochila). Desenvolvido em **Java puro**, com apoio do **Lombok** para reduzir código boilerplate.

## Funcionalidades
- Geração de população inicial aleatória
- Avaliação de aptidão (função objetivo)
- Seleção de indivíduos (roleta viciada)
- Crossover (recombinação genética)
- Mutação de genes
- Evolução por gerações
- Impressão da melhor solução por geração

## Estrutura do Projeto
algoritmo-genetico-java/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com.wallaks.algoritmo_genetico_java/
                ├── Main.java
                ├── AlgoritmoGenetico.java
                ├── Individuo.java
                └── Produto.java

## Lógica
Dado um conjunto de produtos com **valor** e **espaço ocupado**, o algoritmo busca a melhor combinação possível dentro de um limite de espaço definido. O resultado final exibe os produtos escolhidos e o valor total da solução.

## Requisitos
- Java 21 ou superior
- Maven
- IDE (IntelliJ, VS Code, etc.)
- Plugin Lombok instalado (se estiver usando IDE)

## Como Executar
mvn clean compile
mvn exec:java -Dexec.mainClass="com.wallaks.algoritmo_genetico_java.Main"

## Tecnologias
- Java 21
- Maven
- Lombok

## Exemplo de saída
G: 0
Valor: 9910.91
Espaço: 2.99
Cromossomo: [1, 0, 1, 0, 0, ...]

G: 1
Valor: 10845.32
Espaço: 2.97
Cromossomo: [1, 1, 0, 1, 0, ...]

...

##### Melhor solução G -> 4 #####
Valor: 12039.50
Espaço: 2.95
Cromossomo: [1, 0, 1, 0, 1, ...]

## Inspiração
Esse projeto é uma prática de algoritmos de inteligência artificial para fins educacionais e de portfólio.

