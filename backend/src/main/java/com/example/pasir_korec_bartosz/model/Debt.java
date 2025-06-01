package com.example.pasir_korec_bartosz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "debts")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String title; // Nowe pole: opis transakcji

    @ManyToOne
    @JoinColumn(name = "debtor_id")
    private User debtor;

    @ManyToOne
    @JoinColumn(name = "creditor id")
    private User creditor;

    @ManyToOne
    @JoinColumn(name = "group id")
    private Group group;

    private boolean markedAsPaid = false; // dłużnik zaznaczył jako opłacone
    private boolean confirmedByCreditor = false; // wierzyciel zatwierdził opłacenie
}
