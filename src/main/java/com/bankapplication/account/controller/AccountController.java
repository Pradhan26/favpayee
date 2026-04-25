package com.bankapplication.account.controller;

import com.bankapplication.account.service.AccountService;
import com.bankapplication.dto.AccountRequest;
import com.bankapplication.dto.AccountResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{customerId}/create")
    public ResponseEntity<AccountResponse> createAccount(
            @PathVariable String customerId,
            @Valid @RequestBody AccountRequest accountRequest) {
        AccountResponse response = accountService.createAccount(customerId, accountRequest);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Page<AccountResponse>> getAccounts(
            @PathVariable String customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<AccountResponse> response = accountService.getAccounts(customerId, PageRequest.of(page, size));
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/update/{customerId}/{accountId}")
    public ResponseEntity<AccountResponse> updateAccount(
            @PathVariable String customerId,
            @PathVariable Long accountId,
            @Valid @RequestBody AccountRequest accountRequest) {
        AccountResponse response = accountService.updateAccount(customerId, accountId, accountRequest);
        return ResponseEntity.status(202).body(response);
    }

    @DeleteMapping("/remove/{customerId}")
    public ResponseEntity<Void> deleteAccount(
            @PathVariable String customerId,
            @RequestParam String accountId) {
        accountService.deleteAccount(customerId, accountId);
        return ResponseEntity.status(204).build();
    }
}
