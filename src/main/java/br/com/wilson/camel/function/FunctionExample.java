package br.com.wilson.camel.function;

import java.util.List;
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        // Define a função que converte String para Integer
        Function<String, Integer> stringParaInteger = s -> Integer.parseInt(s);

        // Aplica a função
        Integer resultado = stringParaInteger.apply("123");

        System.out.println(resultado);  // Saída: 123

        Function<String, Boolean> stringToBoolean = s -> Boolean.valueOf(s);

        Boolean b = stringToBoolean.apply("true");
        System.out.println(b);

        Function<Integer, Integer> dobrar = x -> x * 2;
        Function<Integer, Integer> quadrado = x -> x * x;

        resultado = dobrar.apply(5); // resultado = 10
        System.out.println("resultado1 : "+ resultado);
        resultado = quadrado.apply(3); // resultado = 9
        System.out.println("resultado2 : "+ resultado);


        Function<String, String> paraMaiusculas = s -> s.toUpperCase();
        Function<String, String> inverter = s -> new StringBuilder(s).reverse().toString();

        String texto = "Olá, mundo!";
        String textoMaiusculo = paraMaiusculas.apply(texto);
        System.out.println("textoMaiusculo : " + textoMaiusculo);
        String textoInvertido = inverter.apply(texto);
        System.out.println("textoInvertido : " + textoInvertido);

        Function<List<Integer>, Integer> somarElementos = lista -> lista.stream().reduce(0, Integer::sum);

        List<Integer> numeros = List.of(1, 2, 3, 4, 5);
        int soma = somarElementos.apply(numeros);
        System.out.println("soma1 : " + soma);

        Function<List<Integer>, Integer> somarElementos2 = lista -> lista.stream().reduce(0, (a, c) -> a+c);
        soma = somarElementos2.apply(numeros);
        System.out.println("soma2 : " + soma);

    }
}
/**
Dicas para Aprofundar:
Pratique: Crie seus próprios exemplos e experimente diferentes combinações de funções.
Explore outras interfaces funcionais:
 Além de Function, existem outras como Predicate, Consumer, Supplier, etc.
Entenda o conceito de currying: Uma técnica de transformação de funções que permite aplicar argumentos parcialmente.
Utilize bibliotecas funcionais: Java 8 introduziu streams e outras funcionalidades funcionais, e existem bibliotecas de terceiros que oferecem ainda mais recursos.
 **/