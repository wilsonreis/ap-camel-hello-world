package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {
    public static void main(String[] args) {
        List<List<Integer>> listaDeListas = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );

        // Unindo todas as listas em uma única lista
        List<Integer> resultado = listaDeListas.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(resultado);  // Saída: [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
