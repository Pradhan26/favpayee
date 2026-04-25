package com.bankapplication.account;

import com.bankapplication.account.entity.Account;
import com.bankapplication.account.repository.AccountRepository;
import com.bankapplication.account.service.AccountService;
import com.bankapplication.dto.AccountRequest;
import com.bankapplication.dto.AccountResponse;
import com.bankapplication.exception.AccountException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    public class AccountServiceTest {

        @Mock
        private AccountRepository repository;

        @InjectMocks
        private AccountService service;

        private AccountRequest request;
        private Account account;

        @BeforeEach
        void setup() {
            request = new AccountRequest();
            request.setCustomerId("C101");
            request.setAccountName("Bridge");
            request.setIban("ES211234");

            account = new Account();
            account.setAccountId(1L);
            account.setCustomerId("C101");
            account.setAccountName("Bridge");
            account.setIbanNumber("ES211234");
            account.setBankName("Nairobi Bank");
        }

        @Test
        void createAccount_success() {

            when(repository.countByCustomerId("C101")).thenReturn(2L);
            when(repository.existsByCustomerIdAndIbanNumber("C101","ES211234"))
                    .thenReturn(false);
            when(repository.save(any(Account.class))).thenReturn(account);

            AccountResponse response =
                    service.createAccount("C101", request);

            assertEquals("Bridge", response.getAccountName());
            verify(repository).save(any(Account.class));
        }

        @Test
        void createAccount_customerMismatch() {
            request.setCustomerId("C999");

            assertThrows(AccountException.class,
                    () -> service.createAccount("C101", request));
        }

        @Test
        void createAccount_maxLimitReached() {

            when(repository.countByCustomerId("C101")).thenReturn(20L);

            assertThrows(AccountException.class,
                    () -> service.createAccount("C101", request));
        }

        @Test
        void createAccount_duplicateIban() {

            when(repository.countByCustomerId("C101")).thenReturn(2L);
            when(repository.existsByCustomerIdAndIbanNumber("C101","ES211234"))
                    .thenReturn(true);

            assertThrows(AccountException.class,
                    () -> service.createAccount("C101", request));
        }

        @Test
        void getAccounts_success() {

            Page<Account> page =
                    new PageImpl<>(List.of(account));

            when(repository.findByCustomerId(eq("C101"), any(Pageable.class)))
                    .thenReturn(page);

            Page<AccountResponse> result =
                    service.getAccounts("C101", PageRequest.of(0,5));

            assertEquals(1, result.getTotalElements());
        }

        @Test
        void updateAccount_success() {

            when(repository.findById(1L))
                    .thenReturn(Optional.of(account));

            when(repository.save(any(Account.class)))
                    .thenReturn(account);

            AccountResponse response =
                    service.updateAccount("C101",1L,request);

            assertNotNull(response);
        }

        @Test
        void updateAccount_notFound() {

            when(repository.findById(1L))
                    .thenReturn(Optional.empty());

            assertThrows(AccountException.class,
                    () -> service.updateAccount("C101",1L,request));
        }

        @Test
        void deleteAccount_success() {

            when(repository.findById(1L))
                    .thenReturn(Optional.of(account));

            service.deleteAccount("C101","1");

            verify(repository).deleteById(1L);
        }

        @Test
        void deleteAccount_invalidId() {

            assertThrows(AccountException.class,
                    () -> service.deleteAccount("C101","abc"));
        }

        @Test
        void deleteAccount_wrongCustomer() {

            account.setCustomerId("C999");

            when(repository.findById(1L))
                    .thenReturn(Optional.of(account));

            assertThrows(AccountException.class,
                    () -> service.deleteAccount("C101","1"));
        }
    }

