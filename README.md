
Predicate<Integer> isEvenAndGreaterThan10 = number -> number % 2 == 0 && number > 10;
Predicate<String> startsWithAOrB = s -> s.startsWith("A") || s.startsWith("B");

Interface	Descrição	Exemplo
Predicate	Verifica se um valor satisfaz uma condição	number -> number > 0
Consumer	Consome um valor, realizando alguma ação	System.out::println
Function	Transforma um valor em outro	s -> s.toUpperCase()
Supplier	Fornece um valor	() -> new Random()


O Consumer é uma interface funcional na linguagem Java que representa uma operação 
que aceita um único argumento de entrada e não retorna nenhum valor. 
É uma das interfaces principais do pacote java.util.function, 
que foi introduzido no Java 8 como parte da API de lambdas e expressões lambda.

Aqui está a definição da interface Consumer:

java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}

Consumer<String> uppercaseConsumer = str -> System.out.println(str.toUpperCase());
Consumer<String> lowercaseConsumer = str -> System.out.println(str.toLowerCase());

// Concatenando dois consumidores
Consumer<String> printBothCases = uppercaseConsumer.andThen(lowercaseConsumer);

printBothCases.accept("Java"); // Imprime: JAVA e depois java
