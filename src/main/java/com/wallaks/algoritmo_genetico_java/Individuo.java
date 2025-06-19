package com.wallaks.algoritmo_genetico_java;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Individuo implements Comparable<Individuo> {
    private List<Double> espacos;
    private List<Double> valores;
    private Double limiteEspacos;
    private Double notaAvaliacao;
    private Double espacoUsado;
    private int geracao;
    private List<String> cromossomo;

    public Individuo(List<Double> espacos, List<Double> valores, Double limiteEspacos) {
        this.espacos = espacos;
        this.valores = valores;
        this.limiteEspacos = limiteEspacos;
        this.notaAvaliacao = 0.0;
        this.espacoUsado = 0.0;
        this.geracao = 0;
        this.cromossomo = new ArrayList<>();
        for (int i = 0; i < this.espacos.size(); ++i) {
            this.cromossomo.add(Math.random() < 0.5 ? "0" : "1");
        }
    }

    public void avaliacao() {
        double nota = 0.0;
        double somaEspacos = 0.0;
        for (int i = 0; i < this.cromossomo.size(); ++i) {
            if ("1".equals(this.cromossomo.get(i))) {
                nota += this.valores.get(i);
                somaEspacos += this.espacos.get(i);
            }
        }
        if (somaEspacos > this.limiteEspacos) {
            nota = 1.0;
        }
        this.notaAvaliacao = nota;
        this.espacoUsado = somaEspacos;
    }

    public List<Individuo> crossover(Individuo outroIndividuo) {
        int corte = (int) Math.round(Math.random() * this.cromossomo.size());
        List<String> filho1Cromossomo = new ArrayList<>();
        filho1Cromossomo.addAll(outroIndividuo.getCromossomo().subList(0, corte));
        filho1Cromossomo.addAll(this.cromossomo.subList(corte, this.cromossomo.size()));

        List<String> filho2Cromossomo = new ArrayList<>();
        filho2Cromossomo.addAll(this.cromossomo.subList(0, corte));
        filho2Cromossomo.addAll(outroIndividuo.getCromossomo().subList(corte, this.cromossomo.size()));

        Individuo filho1 = new Individuo(this.espacos, this.valores, this.limiteEspacos);
        filho1.setCromossomo(filho1Cromossomo);
        filho1.setGeracao(this.geracao + 1);

        Individuo filho2 = new Individuo(this.espacos, this.valores, this.limiteEspacos);
        filho2.setCromossomo(filho2Cromossomo);
        filho2.setGeracao(this.geracao + 1);

        List<Individuo> filhos = new ArrayList<>();
        filhos.add(filho1);
        filhos.add(filho2);
        return filhos;
    }

    public Individuo mutacao(Double taxaMutacao) {
        for (int i = 0; i < this.cromossomo.size(); ++i) {
            if (Math.random() < taxaMutacao) {
                this.cromossomo.set(i, "1".equals(this.cromossomo.get(i)) ? "0" : "1");
            }
        }
        return this;
    }

    @Override
    public int compareTo(Individuo o) {
        return this.notaAvaliacao.compareTo(o.getNotaAvaliacao()) * -1;
    }
}