package com.bankapplication.bank.service;

import com.bankapplication.bank.dto.BankCodeResponseDto;
import com.bankapplication.bank.dto.BankRequest;
import com.bankapplication.bank.entity.Bank;
import com.bankapplication.bank.repository.BankRepository;
import com.bankapplication.exception.BankException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Bank Service Test Suite
 * Tests business logic for bank operations with numeric codes (e.g., 12345)
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Bank Service Tests")
class BankServiceTest {

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankService bankService;

    private Bank testBank;
    private BankRequest bankRequest;

    @BeforeEach
    void setUp() {
        // Setup test data - bank code is numeric (e.g., 12345)
        testBank = new Bank("12345", "Axis Bank Limited");
        bankRequest = new BankRequest();
        bankRequest.setCode("12345");
        bankRequest.setBankName("Axis Bank Limited");
    }

    /**
     * Test Case 1: Get bank by numeric code - Success
     * Expected: Return BankCodeResponseDto with correct bank details
     */
    @Test
    @DisplayName("Should retrieve bank by numeric code successfully")
    void testGetBankByCodeSuccess() {
        when(bankRepository.findByCode("12345")).thenReturn(testBank);

        BankCodeResponseDto response = bankService.getBankByCode("12345");

        assertNotNull(response);
        assertEquals("12345", response.getCode());
        assertEquals("Axis Bank Limited", response.getBankName());
        verify(bankRepository, times(1)).findByCode("12345");
    }

    /**
     * Test Case 2: Get bank with null code
     * Expected: Throw BankException with "Invalid bank code" message
     */
    @Test
    @DisplayName("Should throw exception when bank code is null")
    void testGetBankByCodeWithNullCode() {
        BankException exception = assertThrows(BankException.class, () -> {
            bankService.getBankByCode(null);
        });

        assertEquals("Invalid bank code", exception.getMessage());
        verify(bankRepository, never()).findByCode(any());
    }

    /**
     * Test Case 3: Get bank when code doesn't exist
     * Expected: Throw BankException with "Bank not found" message
     */
    @Test
    @DisplayName("Should throw exception when bank is not found")
    void testGetBankByCodeNotFound() {
        when(bankRepository.findByCode("99999")).thenReturn(null);

        BankException exception = assertThrows(BankException.class, () -> {
            bankService.getBankByCode("99999");
        });

        assertEquals("Bank not found", exception.getMessage());
        verify(bankRepository, times(1)).findByCode("99999");
    }

    /**
     * Test Case 4: Create new bank with numeric code
     * Expected: Save bank and return BankCodeResponseDto
     */
    @Test
    @DisplayName("Should create bank with numeric code successfully")
    void testCreateBankSuccess() {
        when(bankRepository.save(any(Bank.class))).thenReturn(testBank);

        BankCodeResponseDto response = bankService.createBank(bankRequest);

        assertNotNull(response);
        assertEquals("12345", response.getCode());
        assertEquals("Axis Bank Limited", response.getBankName());
        verify(bankRepository, times(1)).save(any(Bank.class));
    }
}

