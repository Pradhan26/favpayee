package com.bankapplication.account.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bankapplication.account.entity.Account;
import com.bankapplication.account.repository.AccountRepository;
import com.bankapplication.dto.AccountRequest;
import com.bankapplication.dto.AccountResponse;
import com.bankapplication.exception.AccountException;

@Service
public class AccountService {

    private static final long MAX_FAVORITES = 20;
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount(String customerId, AccountRequest accountRequest) {
        if (!customerId.equals(accountRequest.getCustomerId())) {
            throw new AccountException("Customer ID mismatch");
        }

        long count = accountRepository.countByCustomerId(customerId);
        if (count >= MAX_FAVORITES) {
            throw new AccountException("Maximum 20 favorite accounts allowed");
        }

        boolean exists = accountRepository.existsByCustomerIdAndIbanNumber(
                customerId,
                accountRequest.getIban());
        if (exists) {
            throw new AccountException("Favorite account already exists for this customer");
        }

        Account account = new Account();
        account.setCustomerId(customerId);
        account.setAccountName(accountRequest.getAccountName());
        account.setIbanNumber(accountRequest.getIban());

        Account savedAccount = accountRepository.save(account);
        return convertToResponse(savedAccount);
    }

    public Page<AccountResponse> getAccounts(String customerId, Pageable pageable) {
        return accountRepository.findByCustomerId(customerId, pageable)
                .map(this::convertToResponse);
    }

    public AccountResponse updateAccount(String customerId, Long id, AccountRequest accountRequest) {
        if (!customerId.equals(accountRequest.getCustomerId())) {
            throw new AccountException("Customer ID mismatch");
        }
        Account existing = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account not found"));

        if (!existing.getCustomerId().equals(customerId)) {
            throw new AccountException("Account does not belong to this customer");
        }

        existing.setAccountName(accountRequest.getAccountName());
        existing.setIbanNumber(accountRequest.getIban());

        Account updatedAccount = accountRepository.save(existing);
        return convertToResponse(updatedAccount);
    }

    public void deleteAccount(String customerId, String accountId) {
        try {
            Long id = Long.parseLong(accountId);

            Account existing = accountRepository.findById(id)
                    .orElseThrow(() -> new AccountException("Account not found"));

            if (!existing.getCustomerId().equals(customerId)) {
                throw new AccountException("Account does not belong to this customer");
            }

            accountRepository.deleteById(id);
        } catch (NumberFormatException e) {
            throw new AccountException("Invalid account ID format");
        }
    }

    private AccountResponse convertToResponse(Account account) {
        AccountResponse response = new AccountResponse();
        response.setId(account.getAccountId());
        response.setAccountName(account.getAccountName());
        response.setIban(account.getIbanNumber());
        response.setBankName(account.getBankName());
        return response;
    }
}
