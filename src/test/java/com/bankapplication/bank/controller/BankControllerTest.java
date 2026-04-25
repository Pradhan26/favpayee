package com.bankapplication.bank.controller;

import com.bankapplication.auth.security.JwtAuthenticationFilter;
import com.bankapplication.bank.dto.BankCodeResponseDto;
import com.bankapplication.bank.dto.BankRequest;
import com.bankapplication.bank.service.BankService;
import com.bankapplication.exception.BankException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Bank Controller Test Suite
 * Tests REST API endpoints for bank code retrieval (numeric codes like 12345)
 */
@WebMvcTest(value = BankController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
@DisplayName("Bank Controller Tests")
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @Autowired
    private ObjectMapper objectMapper;

    private BankCodeResponseDto testResponse;
    private BankRequest bankRequest;

    @BeforeEach
    void setUp() {
        // Setup test data - bank code is numeric (e.g., 12345)
        testResponse = new BankCodeResponseDto("12345", "Axis Bank Limited");
        bankRequest = new BankRequest();
        bankRequest.setCode("12345");
        bankRequest.setBankName("Axis Bank Limited");
    }

    /**
     * Test Case 1: GET /api/bank/{bankCode} - Retrieve bank by numeric code successfully
     * Expected: HTTP 200 with bank details in JSON response
     */
    @Test
    @DisplayName("Should get bank by numeric code successfully")
    void testGetBankByCodeSuccess() throws Exception {
        when(bankService.getBankByCode("12345")).thenReturn(testResponse);

        mockMvc.perform(get("/api/bank/12345")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", equalTo("12345")))
                .andExpect(jsonPath("$.bankName", equalTo("Axis Bank Limited")));

        verify(bankService, times(1)).getBankByCode("12345");
    }

    /**
     * Test Case 2: GET /api/bank/{bankCode} - Bank code not found
     * Expected: HTTP 400 bad request with error message (as per GlobalExceptionHandler)
     */
    @Test
    @DisplayName("Should throw exception when bank code not found")
    void testGetBankByCodeNotFound() throws Exception {
        when(bankService.getBankByCode("99999"))
                .thenThrow(new BankException("Bank not found"));

        mockMvc.perform(get("/api/bank/99999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(bankService, times(1)).getBankByCode("99999");
    }

    /**
     * Test Case 3: POST /api/bank - Add new bank with valid numeric code
     * Expected: HTTP 200 with success message
     */
    @Test
    @DisplayName("Should add bank with valid numeric code successfully")
    void testAddBankSuccess() throws Exception {
        BankRequest request = new BankRequest();
        request.setCode("54321");
        request.setBankName("ICICI Bank Limited");

        mockMvc.perform(post("/api/bank")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    /**
     * Test Case 4: POST /api/bank - Validation for missing required fields
     * Expected: HTTP 400 bad request when bank code is null
     */
    @Test
    @DisplayName("Should return bad request when bank code is missing")
    void testAddBankWithMissingCode() throws Exception {
        BankRequest invalidRequest = new BankRequest();
        invalidRequest.setBankName("Bank Name Only");

        mockMvc.perform(post("/api/bank")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}
