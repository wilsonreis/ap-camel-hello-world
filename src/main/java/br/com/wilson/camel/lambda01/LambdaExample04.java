package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaExample04 {
    public static void main(String[] args) {
        List<String> nomes = Arrays.asList("Maria", "Ana", "Carlos");

        // Ordena por ordem alfabética inversa usando lambda
        Collections.sort(nomes, (a, b) -> b.compareTo(a));

        System.out.println(nomes);  // Saída: [Maria, Carlos, Ana]
    }
}
