package br.com.wilson.camel.consumer;

import br.com.wilson.camel.variados.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample02 {
    public static void main(String[] args) {

        Consumer<User> atualizadorDeNome = pessoa -> pessoa.setName("Novo Nome");
        User pessoa = new User("Nome Antigo", 1);
        atualizadorDeNome.accept(pessoa); // Agora pessoa.getNome() é "Novo Nome"

        List<String> nomes = Arrays.asList("Ana", "Pedro", "Carlos");
        List<String> nomesPrefixados = new ArrayList<>();
        Consumer<String> adicionaPrefixo = nome -> nomesPrefixados.add("Sr(a). " + nome);
        nomes.forEach(adicionaPrefixo); // Adiciona "Sr(a). " antes de cada nome em nomesPrefixados

        // Criando um Consumer para imprimir cada nome
        Consumer<String> imprimirNome = nome -> System.out.println("Olá, " + nome + "!");

        // Aplicando o Consumer à lista
        nomes.forEach(imprimirNome);

        System.out.println("Imprimindo com utilização de Consumer!");
        List<User> userList = List.of(
                new User("wilson", 1),
                new User("wagner", 20),
                new User("jessica", 3),
                new User("debora", 4)
        );
        // Create a Consumer to process each valid user
        Consumer<User> processUser = user -> {
            user.setName(user.getName().toUpperCase());
            System.out.println(user);
        };

        userList.stream()
                .filter(User::isValid) // Filter users using the isValid method
                .forEach(processUser);   // Apply Consumer to each valid user



        Consumer<String> uppercaseConsumer = str -> System.out.println(str.toUpperCase());
        Consumer<String> lowercaseConsumer = str -> System.out.println(str.toLowerCase());

        // Concatenando dois consumidores
        Consumer<String> printBothCases = uppercaseConsumer.andThen(lowercaseConsumer);

        printBothCases.accept("Java"); // Imprime: JAVA e depois java

        System.out.println("Consumer sem print imediato, com manipulação dos dados");
        Consumer<User> manipulaDadosUsuario = user -> {
            user.setName(user.getName().toUpperCase());
        };

    }
}
