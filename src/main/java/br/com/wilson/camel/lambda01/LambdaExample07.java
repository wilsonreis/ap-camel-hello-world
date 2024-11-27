package br.com.wilson.camel.lambda01;

import java.util.function.BiFunction;

public class LambdaExample07 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> soma = (a, b) -> a + b;

        System.out.println(soma.apply(3, 7));  // Saída: 10
    }
}

