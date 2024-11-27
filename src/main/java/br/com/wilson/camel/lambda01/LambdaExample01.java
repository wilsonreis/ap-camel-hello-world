package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.List;

public class LambdaExample01 {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        // Com lambda
        numeros.forEach(n -> System.out.println(n));
    }
}
