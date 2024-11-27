package br.com.wilson.camel.predicate;

public class Variados {

}
/*
Predicate<Integer> isEvenAndGreaterThan10 = number -> number % 2 == 0 && number > 10;
Predicate<String> startsWithAOrB = s -> s.startsWith("A") || s.startsWith("B");

Interface	Descrição	Exemplo
Predicate	Verifica se um valor satisfaz uma condição	number -> number > 0
Consumer	Consome um valor, realizando alguma ação	System.out::println
Function	Transforma um valor em outro	s -> s.toUpperCase()
Supplier	Fornece um valor	() -> new Random()
 */
