package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample01 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(5, 3, 8, 1, 9, 2, 4, 7);

        // Filtra números pares, ordena e transforma em lista
        List<Integer> paresOrdenados = numeros.stream()
                .filter(n -> n % 2 == 0)  // Filtra números pares
                .sorted()                 // Ordena os números
                .collect(Collectors.toList());

        System.out.println(paresOrdenados);  // Saída: [2, 4, 8]
    }
}
