package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExample02 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Filtra números pares
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0) // Lambda que retorna true para números pares
                .collect(Collectors.toList());

        System.out.println(pares);  // Saída: [2, 4, 6]
    }
}
