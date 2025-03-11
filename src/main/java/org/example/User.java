package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class User {
    String fullName;
    LocalDate birthDate;
    String phoneNumber;
    Integer salary;
    String gender;

    public User(String fullName, LocalDate birthDate, String phoneNumber, Integer salary, String gender) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.gender = gender;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public boolean hasValidPhone() {
        return phoneNumber != null;
    }

    @Override
    public String toString() {
        return String.format("User{ФИО='%s', Дата рождения=%s, Телефон='%s', Зарплата=%d, Пол='%s'}",
                fullName, birthDate, phoneNumber, salary, gender);
    }
}
