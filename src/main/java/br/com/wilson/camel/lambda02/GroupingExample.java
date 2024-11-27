package br.com.wilson.camel.lambda02;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingExample {
    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("apple", "apricot", "banana", "blueberry", "cherry");

        // Agrupa as palavras pela primeira letra
        Map<Character, List<String>> agrupadoPorLetra = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra.charAt(0)));

        System.out.println(agrupadoPorLetra);
        // Saída: {a=[apple, apricot], b=[banana, blueberry], c=[cherry]}
    }
}
