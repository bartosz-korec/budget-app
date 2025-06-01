package com.example.pasir_korec_bartosz.dto;

import com.example.pasir_korec_bartosz.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GroupDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner id")
    private User owner; // Właściciel grupy

    private LocalDateTime createdAt = LocalDateTime.now();

}