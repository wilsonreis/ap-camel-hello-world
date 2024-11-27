package br.com.wilson.camel.variados;

import java.util.Objects;
import java.util.function.Predicate;

public class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public boolean isValid() {
        Predicate<User> isAdult = user -> user.getAge() >= 18;
        Predicate<User> hasValidName = user -> user.getName() != null && !user.getName().isEmpty();
        return isAdult.and(hasValidName).test(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public int compareTo(User other) {
        return Integer.compare(this.age, other.age);
    }

}
