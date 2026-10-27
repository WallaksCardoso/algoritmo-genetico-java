package com.wallaks.algoritmo_genetico_java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AlgoritmoGeneticoTest {

    private final List<Double> espacos = List.of(1.0, 2.0, 0.5);
    private final List<Double> valores = List.of(100.0, 300.0, 50.0);

    private Individuo individuo(String... genes) {
        Individuo individuo = new Individuo(espacos, valores, 3.0);
        individuo.setCromossomo(new ArrayList<>(List.of(genes)));
        return individuo;
    }

    @Test
    void avaliacaoSomaValorEEspacoDosItensEscolhidos() {
        Individuo individuo = individuo("1", "0", "1");

        individuo.avaliacao();

        assertEquals(150.0, individuo.getNotaAvaliacao());
        assertEquals(1.5, individuo.getEspacoUsado());
    }

    @Test
    void solucaoQuePassaDoLimiteEPenalizada() {
        Individuo individuo = individuo("1", "1", "1");

        individuo.avaliacao();

        assertEquals(1.0, individuo.getNotaAvaliacao());
        assertEquals(3.5, individuo.getEspacoUsado());
    }

    @Test
    void crossoverGeraDoisFilhosComOsGenesDosPais() {
        Individuo pai = individuo("1", "1", "1");
        Individuo mae = individuo("0", "0", "0");

        List<Individuo> filhos = pai.crossover(mae);

        assertEquals(2, filhos.size());
        for (Individuo filho : filhos) {
            assertEquals(3, filho.getCromossomo().size());
            assertEquals(1, filho.getGeracao());
        }
        long uns = filhos.stream().flatMap(f -> f.getCromossomo().stream()).filter("1"::equals).count();
        assertEquals(3, uns);
    }

    @Test
    void mutacaoComTaxaZeroNaoMudaNada() {
        Individuo individuo = individuo("1", "0", "1");

        individuo.mutacao(0.0);

        assertEquals(List.of("1", "0", "1"), individuo.getCromossomo());
    }

    @Test
    void mutacaoComTaxaTotalInverteTodosOsGenes() {
        Individuo individuo = individuo("1", "0", "1");

        individuo.mutacao(1.0);

        assertEquals(List.of("0", "1", "0"), individuo.getCromossomo());
    }

    @Test
    void populacaoImparMantemOTamanhoEmTodasAsGeracoes() {
        AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(3);

        algoritmo.resolver(0.05, 10, espacos, valores, 3.0);

        assertEquals(3, algoritmo.getPopulacao().size());
    }

    @Test
    void resolverDevolveUmGenePorProduto() {
        AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(10);

        List<String> solucao = algoritmo.resolver(0.05, 20, espacos, valores, 3.0);

        assertEquals(3, solucao.size());
        assertTrue(solucao.stream().allMatch(g -> g.equals("0") || g.equals("1")));
    }
}
