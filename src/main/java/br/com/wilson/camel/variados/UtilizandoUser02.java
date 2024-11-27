package br.com.wilson.camel.variados;

import java.util.ArrayList;
import java.util.List;

public class UtilizandoUser02 {

    public static void main(String[] args) {
        List<User> userList = List.of(
                new User("wilson", 1),
                new User("wagner", 20),
                new User("jessica", 3),
                new User("debora", 4)
        );

        System.out.println("Todos os usuários:");
        for (User user : userList) {
            System.out.println(user);
        }

        // Imprimindo usuários adultos
        System.out.println("\nUsuários adultos:");
        userList.stream().filter(User::isValid).forEach(System.out::println);
    }

}
