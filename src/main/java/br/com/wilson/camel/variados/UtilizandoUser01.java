package br.com.wilson.camel.variados;

import java.util.ArrayList;
import java.util.List;

public class UtilizandoUser01 {
    static User user01;
    static User user02;
    static User user03;
    static User user04;
    static List<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        user01 = new User("wilson", 1);
        user02 = new User("wagner", 20);
        user03 = new User("jessica", 3);
        user04 = new User("debora", 4);
        userList.add(user01);
        userList.add(user02);
        userList.add(user03);
        userList.add(user04);

        System.out.println("Todos os usuários:");
        for (User user : userList) {
            System.out.println(user);
        }

        // Imprimindo usuários adultos
        System.out.println("\nUsuários adultos:");
        userList.stream().filter(User::isValid).forEach(System.out::println);
    }

}
