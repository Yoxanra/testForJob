package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class UserProcessor {
    public static void main(String[] args) throws IOException {
        List<String[]> rawData = Files.lines(Paths.get("src/main/resources/data.txt"))
                .map(line -> Arrays.copyOf(line.split(";", -1), 4))
                .toList();

        List<ValidationResult> results = rawData.stream()
                .map(UserFactory::createUser)
                .toList();

        List<User> users = results.stream()
                .map(ValidationResult::user)
                .toList();

        List<User> validUsers = results.stream()
                .filter(result -> result.errors().isEmpty())
                .map(ValidationResult::user)
                .toList();

        List<ValidationResult> invalidUsers = results.stream()
                .filter(result -> !result.errors().isEmpty())
                .toList();

        long maleCount = users.stream().filter(user -> user.getGender().equals("Мужчина")).count();
        long femaleCount = users.stream().filter(user -> user.getGender().equals("Женщина")).count();
        long ageAbove25 = validUsers.stream().filter(user -> user.getAge() > 25).count();
        double avgSalary = validUsers.stream().mapToInt(User::getSalary).average().orElse(0);
        long femaleWithValidPhone = users.stream()
                .filter(user -> user.hasValidPhone() && user.getGender().equals("Женщина"))
                .count();


        System.out.println("Все пользователи:");
        users.forEach(System.out::println);

        System.out.println("\nКоличество мужчин: " + maleCount);
        System.out.println("Количество женщин: " + femaleCount);
        System.out.println("Количество пользователей старше 25 лет: " + ageAbove25);
        System.out.println("Средняя заработная плата: " + avgSalary);
        System.out.println("Количество женщин с валидным телефоном: " + femaleWithValidPhone);

        System.out.println("\nНекорректные записи:");
        invalidUsers.forEach(result ->
                System.out.println(result.user().toString() + " - Ошибки: " + String.join(", ", result.errors())));
    }
}
