package com.example.pasir_korec_bartosz.repository;

import com.example.pasir_korec_bartosz.model.Group;
import com.example.pasir_korec_bartosz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByMemberships_User(User membershipsUser);
}
