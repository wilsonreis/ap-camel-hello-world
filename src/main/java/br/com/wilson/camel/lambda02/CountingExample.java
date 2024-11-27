package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;

public class CountingExample {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "stream", "parallel", "lambda", "collector");

        long count = palavras.stream()
                .filter(palavra -> palavra.length() > 5) // Filtra palavras com mais de 5 letras
                .count();

        System.out.println(count);  // Saída: 3
    }
}
