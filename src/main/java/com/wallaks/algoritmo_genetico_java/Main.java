package com.wallaks.algoritmo_genetico_java;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produto("Geladeira Dako", 0.751, 999.9));
        listaProdutos.add(new Produto("Iphone 6", 8.9E-5, 2911.12));
        listaProdutos.add(new Produto("TV 55' ", 0.4, 4346.99));
        listaProdutos.add(new Produto("TV 50' ", 0.29, 3999.9));
        listaProdutos.add(new Produto("TV 42' ", 0.2, 2999.0));
        listaProdutos.add(new Produto("Notebook Dell", 0.0035, 2499.9));
        listaProdutos.add(new Produto("Ventilador Panasonic", 0.496, 199.9));
        listaProdutos.add(new Produto("Microondas Electrolux", 0.0424, 308.66));
        listaProdutos.add(new Produto("Microondas LG", 0.0544, 429.9));
        listaProdutos.add(new Produto("Microondas Panasonic", 0.0319, 299.29));
        listaProdutos.add(new Produto("Geladeira Brastemp", 0.635, 849.0));
        listaProdutos.add(new Produto("Geladeira Consul", 0.87, 1199.89));
        listaProdutos.add(new Produto("Notebook Lenovo", 0.498, 1999.9));
        listaProdutos.add(new Produto("Notebook Asus", 0.527, 3999.0));

        List<String> resultado = getStrings(listaProdutos);

        for (int i = 0; i < listaProdutos.size(); ++i) {
            if (resultado.get(i).equals("1")) {
                System.out.println("Nome: " + listaProdutos.get(i).getNome());
            }
        }
    }

    private static List<String> getStrings(List<Produto> listaProdutos) {
        List<Double> espacos = new ArrayList<>();
        List<Double> valores = new ArrayList<>();
        List<String> nomes = new ArrayList<>();

        for (Produto produto : listaProdutos) {
            espacos.add(produto.getEspaco());
            valores.add(produto.getValor());
            nomes.add(produto.getNome());
        }

        Double limite = 3.0;
        int tamanhoPopulacao = 3;
        Double taxaMutacao = 0.05;
        int numeroGeracoes = 4;
        AlgoritmoGenetico ag = new AlgoritmoGenetico(tamanhoPopulacao);
        return ag.resolver(taxaMutacao, numeroGeracoes, espacos, valores, limite);
    }
}