package br.com.wilson.camel.collectors;

import br.com.wilson.camel.variados.User;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Collector01 {
    public static void main(String[] args) {
        List<User> userList = List.of(
                new User("wilson", 1),
                new User("wagner", 40),
                new User("jessica", 30),
                new User("debora", 4)
        );
        // Create a Consumer to process each valid user
        Consumer<User> processUser = user -> {
            user.setName(user.getName().toUpperCase());
        };

        List<User> userList1 =  userList.stream()
                .filter(User::isValid)
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());


        userList1.stream().forEach(processUser);

        System.out.println(userList1);  // Saída: [2, 4, 8]

        userList1 =  userList.stream()
                .filter(User::isValid)
                .sorted(Comparator.comparingInt(User::getAge).reversed())
                .collect(Collectors.toList());
        System.out.println(userList1);  // Saída: [2, 4, 8]
    }
}

/*
List<User> userList1 = userList.stream()
    .filter(User::isValid)
    .sorted(Comparator.comparingInt(User::getAge)
            .thenComparing(Comparator.comparingInt(User::getSalary).reversed())
            .thenComparing(User::getName))
    .collect(Collectors.toList());
 */