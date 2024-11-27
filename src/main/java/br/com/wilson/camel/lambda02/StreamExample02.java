package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;

public class StreamExample02 {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("java", "stream", "api", "parallel", "mais_um");

        // Transforma em maiúsculas e concatena
        String resultado = palavras.stream()
                .map(String::toUpperCase) // Transforma cada string em maiúscula
                .reduce("", (a,b) -> a + b + " "); // Concatena com espaço entre palavras

        System.out.println(resultado);  // Saída: "JAVA STREAM API PARALLEL "
    }
}
