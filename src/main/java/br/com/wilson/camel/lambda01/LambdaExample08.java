package br.com.wilson.camel.lambda01;

import java.util.Arrays;
import java.util.List;


public class LambdaExample08 {
        public static void main(String[] args) {
            List<String> palavras = Arrays.asList("banana", "maçã", "uva");

            // Ordena palavras por tamanho usando um lambda
            palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

            System.out.println(palavras);  // Saída: [uva, maçã, banana]
        }
    }
