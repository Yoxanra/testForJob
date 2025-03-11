package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserFactory {

    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10}");

    public static ValidationResult createUser(String[] data) {
        List<String> errors = new ArrayList<>();
        String fullName = data[0].trim();
        String gender = determineGender(fullName);

        LocalDate birthDate = null;
        try {
            birthDate = LocalDate.parse(data[1].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {
            errors.add("Неверный формат даты");
        }

        String phone = data[2].replaceAll("\\D", "");
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            errors.add("Некорректный номер телефона");
            phone = null;
        }

        Integer salary = null;
        try {
            salary = Integer.parseInt(data[3].trim());
        } catch (NumberFormatException e) {
            errors.add("Некорректная зарплата");
        }

        User user = new User(fullName, birthDate, phone, salary, gender);
        return new ValidationResult(user, errors);
    }

    private static String determineGender(String fullName) {
        String fatherName = fullName.split(" ")[2].toLowerCase();
        return (fatherName.endsWith("на")) ? "Женщина" : "Мужчина";
    }
}
