package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaExampleMetodo {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        // Com lambda
        numeros.forEach(getIntegerConsumer());
    }

    private static Consumer<Integer> getIntegerConsumer() {
        return n -> {
            n = n * 3;
            System.out.println(n);
        };
    }
}
