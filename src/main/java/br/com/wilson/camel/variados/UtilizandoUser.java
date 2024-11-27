package br.com.wilson.camel.variados;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class UtilizandoUser {

    static User user01;
    static User user02;
    static User user03;
    static User user04;
    static Map<String, User> userMap = new HashMap<>();

    public static void main(String[] args) {
        user01 = new User("wilson", 1);
        user02 = new User("wagner", 20);
        user03 = new User("jessica", 3);
        user04 = new User("debora", 4);

        // Add users to Map with unique keys (assuming usernames are unique)
        userMap.put(user01.getName(), user01);
        userMap.put(user02.getName(), user02);
        userMap.put(user03.getName(), user03);
        userMap.put(user04.getName(), user04);

        System.out.println("Todos os usuários:");
        for (User user : userMap.values()) {
            System.out.println(user);
        }

        // Filtering by Predicate with forEach (might need adjustments depending on your use case)
        System.out.println("\nUsuários adultos:");
        //Predicate<User> isAdult = user -> user.getAge() >= 18;
        System.out.println("Imprimindo a coleção de válidos");
        userMap.values().stream().filter(User::isValid).forEach(System.out::println);

        // Filtering by key using getOrDefault (assuming usernames are unique)
        System.out.println("\nUsuário 'wagner':");
        User adultUser = userMap.getOrDefault("wagner", null);
        if (adultUser != null) {
            System.out.println(adultUser);
        } else {
            System.out.println("Usuário não encontrado");
        }
        userMap.values().stream().filter(User::isValid).forEach(System.out::println);
    }
}