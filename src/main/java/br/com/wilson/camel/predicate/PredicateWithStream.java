package br.com.wilson.camel.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateWithStream {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Predicate<Integer> isGreaterThanThree = number -> number > 3;

        List<Integer> filteredNumbers = numbers.stream()
                .filter(isGreaterThanThree)
                .collect(Collectors.toList());

        System.out.println(filteredNumbers); // [4, 5, 6]
    }
}
