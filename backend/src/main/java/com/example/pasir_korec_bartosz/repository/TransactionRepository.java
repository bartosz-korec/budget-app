package com.example.pasir_korec_bartosz.repository;

import com.example.pasir_korec_bartosz.model.Transaction;
import com.example.pasir_korec_bartosz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUser(User user);

    List<Transaction> findByUser(User user);
}
