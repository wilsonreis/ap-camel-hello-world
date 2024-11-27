package br.com.wilson.camel.consumer;

import br.com.wilson.camel.variados.CamelCaseConverter;
import br.com.wilson.camel.variados.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample03 {
    public static void main(String[] args) {
        List<User> userList = List.of(
                new User("wilson", 1),
                new User("wagner", 20),
                new User("jessica", 3),
                new User("debora", 4)
        );

        List<User> userList1 = new ArrayList<>(4);

        Consumer<User> adicionaPrefixoNoNome = user -> {

            user.setName("Sr(a). " + CamelCaseConverter.toCamelCase(user.getName()));
            userList1.add(user);
            System.out.println(user);
        };

        userList.forEach(adicionaPrefixoNoNome); // Adiciona "Sr(a). " antes de cada nome em nomesPrefixados

        for (User user: userList1){
            System.out.println(user);
        }

        Consumer<User> alteraPrefixoNoNome = user -> {
            user.setName("Sr(a). " + CamelCaseConverter.toCamelCase(user.getName()));
            //System.out.println(user);
        };

        System.out.println("Sem criar nova lista");
        userList.stream().forEach(alteraPrefixoNoNome);

        System.out.println("Sem criar nova lista com isValid ");
        userList.stream().filter(User::isValid).forEach(alteraPrefixoNoNome);

    }
}
