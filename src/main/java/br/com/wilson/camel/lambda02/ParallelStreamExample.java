package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        // Calcula o quadrado de cada número em paralelo
        List<Integer> quadrados = numeros.parallelStream()
                .map(n -> n * n) // Calcula o quadrado
                .collect(Collectors.toList());

        System.out.println(quadrados);  // Saída: [1, 4, 9, 16, 25]
    }
}
