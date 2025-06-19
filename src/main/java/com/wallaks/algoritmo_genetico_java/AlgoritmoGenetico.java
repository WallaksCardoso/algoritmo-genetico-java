package com.wallaks.algoritmo_genetico_java;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class AlgoritmoGenetico {
    private int tamanhoPopulacao;
    private List<Individuo> populacao = new ArrayList<>();
    private int geracao;
    private Individuo melhorSolucao;

    public AlgoritmoGenetico(int tamanhoPopulacao) {
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    public void inicializaPopulacao(List<Double> espacos, List<Double> valores, Double limiteEspacos) {
        for (int i = 0; i < this.tamanhoPopulacao; ++i) {
            this.populacao.add(new Individuo(espacos, valores, limiteEspacos));
        }
        this.melhorSolucao = this.populacao.get(0);
    }

    public void ordenaPopulacao() {
        Collections.sort(this.populacao);
    }

    public void melhorIndividuo(Individuo individuo) {
        if (individuo.getNotaAvaliacao() > this.melhorSolucao.getNotaAvaliacao()) {
            this.melhorSolucao = individuo;
        }
    }

    public Double somaAvaliacoes() {
        double soma = 0.0;
        for (Individuo individuo : this.populacao) {
            soma += individuo.getNotaAvaliacao();
        }
        return soma;
    }

    public int selecionaPai(Double somaAvaliacao) {
        int pai = -1;
        double valorSorteado = Math.random() * somaAvaliacao;
        double soma = 0.0;
        for (int i = 0; i < this.populacao.size() && soma < valorSorteado; ++i) {
            soma += this.populacao.get(i).getNotaAvaliacao();
            ++pai;
        }
        return pai;
    }

    public void visualizaGeracao() {
        Individuo melhor = this.populacao.getFirst();
        System.out.println("G: " + melhor.getGeracao() +
                "\nValor: " + melhor.getNotaAvaliacao() +
                "\nEspaço: " + melhor.getEspacoUsado() +
                "\nCromossomo: " + melhor.getCromossomo());
    }

    public List<String> resolver(Double taxaMutacao, int numeroGeracoes, List<Double> espacos, List<Double> valores, Double limiteEspacos) {
        this.inicializaPopulacao(espacos, valores, limiteEspacos);

        for (Individuo individuo : this.populacao) {
            individuo.avaliacao();
        }

        this.ordenaPopulacao();
        this.visualizaGeracao();

        for (int geracao = 0; geracao < numeroGeracoes; ++geracao) {
            Double somaAvaliacao = this.somaAvaliacoes();
            List<Individuo> novaPopulacao = new ArrayList<>();

            for (int i = 0; i < this.populacao.size() / 2; ++i) {
                int pai1 = this.selecionaPai(somaAvaliacao);
                int pai2 = this.selecionaPai(somaAvaliacao);
                List<Individuo> filhos = this.populacao.get(pai1).crossover(this.populacao.get(pai2));
                novaPopulacao.add(filhos.get(0).mutacao(taxaMutacao));
                novaPopulacao.add(filhos.get(1).mutacao(taxaMutacao));
            }

            this.setPopulacao(novaPopulacao);

            for (Individuo individuo : this.populacao) {
                individuo.avaliacao();
            }

            this.ordenaPopulacao();
            this.visualizaGeracao();
            Individuo melhor = this.populacao.getFirst();
            this.melhorIndividuo(melhor);
        }

        System.out.println("##### Melhor solução G -> " + this.melhorSolucao.getGeracao() + " #####"
                + "\nValor: " + this.melhorSolucao.getNotaAvaliacao()
                + "\nEspaço: " + this.melhorSolucao.getEspacoUsado()
                + "\nCromossomo: " + this.melhorSolucao.getCromossomo() + "\n");
        return this.melhorSolucao.getCromossomo();
    }
}