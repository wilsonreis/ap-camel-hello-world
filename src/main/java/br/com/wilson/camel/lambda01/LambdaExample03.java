package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaExample03 {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "lambda", "stream");

        // Transforma em maiúsculas
        List<String> palavrasMaiusculas = palavras.stream()
                .map(s -> s.toUpperCase()) // Lambda que transforma a string para maiúsculas
                .collect(Collectors.toList());

        System.out.println(palavrasMaiusculas);  // Saída: [JAVA, LAMBDA, STREAM]

        palavras.stream().map(s -> s.toUpperCase()).collect(Collectors.toList()).forEach(System.out::println);
    }
}
