package com.example.kassa.repository;

import com.example.kassa.entity.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashierRepository extends JpaRepository<Cashier, Long> {
    Cashier findByUsername(String username);
}
