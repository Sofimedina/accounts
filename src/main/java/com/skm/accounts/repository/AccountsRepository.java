package com.skm.accounts.repository;

import com.skm.accounts.entity.Accounts;
import com.skm.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findByCustomerId(Long id);
}
