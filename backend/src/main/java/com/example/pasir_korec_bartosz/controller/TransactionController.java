package com.example.pasir_korec_bartosz.controller;

import com.example.pasir_korec_bartosz.dto.TransactionDTO;
import com.example.pasir_korec_bartosz.model.Transaction;
import com.example.pasir_korec_bartosz.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TransactionController obsługuje żądania HTTP związane z operacjami CRUD dla transakcji.
 */

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // 📌 1. Pobieranie wszystkich transakcji (GET)
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    // 📌 2. Pobieranie pojedynczej transakcji po ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    // 📌 3. Aktualizacja istniejącej transakcji (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(
            @PathVariable Long id,
            @Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, transactionDTO);
        return ResponseEntity.ok(updatedTransaction);
    }

    // 📌 4. Dodawanie nowej transakcji (POST)
    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction savedTransaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.ok(savedTransaction);
    }


    // 📌 5. Usuwanie transakcji (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
