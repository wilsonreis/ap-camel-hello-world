package br.com.wilson.camel.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicatestartsWithA {
    public static void main(String[] args) {
        List<String> paises = List.of("Brasil", "França", "Alemanha", "Portugal");
        Predicate<String> startsWithA = s -> s.startsWith("A");
        List<String> listStartsWithA = paises.stream().
                filter(startsWithA).
                collect(Collectors.toList());
        System.out.println("listStartsWithA : " + listStartsWithA);
    }

    private static Predicate<String> getA() {
        return s -> isA(s);
    }

    private static boolean isA(String s) {
        return s.startsWith("A");
    }
}
