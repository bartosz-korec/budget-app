package com.example.pasir_korec_bartosz.service;

import com.example.pasir_korec_bartosz.dto.GroupTransactionDTO;
import com.example.pasir_korec_bartosz.dto.TransactionDTO;
import com.example.pasir_korec_bartosz.model.*;
import com.example.pasir_korec_bartosz.repository.DebtRepository;
import com.example.pasir_korec_bartosz.repository.GroupRepository;
import com.example.pasir_korec_bartosz.repository.MembershipRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupTransactionService {

    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;
    private final DebtRepository debtRepository;
    private final TransactionService transactionService;

    public GroupTransactionService(GroupRepository groupRepository, MembershipRepository membershipRepository, DebtRepository debtRepository, TransactionService transactionService) {
        this.groupRepository = groupRepository;
        this.membershipRepository = membershipRepository;
        this.debtRepository = debtRepository;
        this.transactionService = transactionService;
    }

    public void addGroupTransaction(GroupTransactionDTO dto, User currentUser) {
        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono grupy"));

        List<Membership> members = membershipRepository.findByGroupId(group.getId());
        List<Long>selectedUserIds=dto.getSelectedUserIds();

        if (selectedUserIds ==null || selectedUserIds.isEmpty()) {
            throw new IllegalArgumentException("Nie wybrano żadnych użytkowników!");
        }

        double amountPerUser = dto.getAmount() / members.size();

        for (Membership member : members) {
            User debtor = member.getUser();
            if (!debtor.getId().equals(currentUser.getId()) && selectedUserIds.contains(debtor.getId())) {
                Debt debt = new Debt();
                debt.setDebtor(debtor);
                debt.setCreditor(currentUser);
                debt.setAmount(amountPerUser);
                debt.setGroup(group);
                debt.setTitle(dto.getTitle());
                transactionService.createTransaction(
                        new TransactionDTO(
                                amountPerUser,
                                TransactionType.EXPENSE,
                                dto.getTitle(),
                                "GROUP",
                                LocalDateTime.now()
                        )
                );
                debtRepository.save(debt);
            }
        }
    }
}