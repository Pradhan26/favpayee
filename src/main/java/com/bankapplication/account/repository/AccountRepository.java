package com.bankapplication.account.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapplication.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Page<Account> findByCustomerId(String customerId, Pageable pageable);

	long countByCustomerId(String customerId);

	boolean existsByCustomerIdAndIbanNumber(String customerId, String ibanNumber);

	boolean existsByCustomerIdAndId(String customerId, Long id);
}
