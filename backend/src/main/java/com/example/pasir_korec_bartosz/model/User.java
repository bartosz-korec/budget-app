package com.example.pasir_korec_bartosz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // informuje Springa, że klasa jest encją mapowaną na tabele w bazie danych
@Table(name = "users") // ustalamy nazwe tabeli
public class User {

    @Id // pole 'id' to klucz główny
    @GeneratedValue(strategy = GenerationType.IDENTITY) // wartość id będzie generowana automatycznie przez baze
    private Long id;

    @NotBlank(message = "Nazwa uzytkownika jest wymagana")
    private String username;

    @Email(message = "Podaj poprawny adres e-mail")
    @NotBlank(message = "Email jest wymagany")
    private String email;

    @NotBlank(message = "Haslo nie może być puste")
    private String password;

    private String currency = "PLN"; // domyślna waluta - do wstępnych ustawień konta
}