package com.przemyslawostrouch.homebudgetassistant.register.repository;

import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
