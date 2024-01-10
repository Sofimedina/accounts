package com.skm.accounts.repository;

import com.skm.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long id);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);

    //JPQL with index param
    @Query("SELECT a FROM Accounts a WHERE a.customerId = ?1 AND a.accountNumber = ?2")
    Optional<Accounts> findByJPQL(Long customerId, Long accountNumber);

    //JPQL with named param
    @Query("SELECT a FROM Accounts a WHERE a.customerId = :customerId AND a.accountNumber = :accountNumber")
    Optional<Accounts> findByJPQLNamedParams(@Param(("customerId")) Long customerId,@Param(("accountNumber")) Long accountNumber);
}
