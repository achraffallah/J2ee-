package org.sid.oulaizebankingbackend.repositories;

import org.sid.oulaizebankingbackend.entities.BankAccount;
import org.sid.oulaizebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}