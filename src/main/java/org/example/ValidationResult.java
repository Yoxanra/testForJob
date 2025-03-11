package org.example;

import java.util.List;

 public record ValidationResult(User user, List<String> errors) {}
