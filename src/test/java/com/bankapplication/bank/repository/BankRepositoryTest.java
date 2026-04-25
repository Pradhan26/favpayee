package com.bankapplication.bank.repository;

import com.bankapplication.bank.entity.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Bank Repository Test Suite
 * Tests database operations for bank entities with numeric codes (e.g., 12345)
 */
@DataJpaTest
@DisplayName("Bank Repository Tests")
class BankRepositoryTest {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Bank testBank;

    @BeforeEach
    void setUp() {
        // Setup test data - bank code is numeric (e.g., 12345)
        testBank = new Bank("12345", "Axis Bank Limited");
    }

    /**
     * Test Case 1: Save a bank entity to database
     * Expected: Bank is saved successfully with all fields populated
     */
    @Test
    @DisplayName("Should save bank to database successfully")
    void testSaveBank() {
        Bank savedBank = bankRepository.save(testBank);

        assertNotNull(savedBank);
        assertEquals("12345", savedBank.getCode());
        assertEquals("Axis Bank Limited", savedBank.getBankName());
    }

    /**
     * Test Case 2: Find bank by numeric code
     * Expected: Return bank entity with matching code
     */
    @Test
    @DisplayName("Should find bank by numeric code")
    void testFindByCode() {
        entityManager.persistAndFlush(testBank);

        Bank foundBank = bankRepository.findByCode("12345");

        assertNotNull(foundBank);
        assertEquals("12345", foundBank.getCode());
        assertEquals("Axis Bank Limited", foundBank.getBankName());
    }

    /**
     * Test Case 3: Find non-existent bank code
     * Expected: Return null when bank code doesn't exist
     */
    @Test
    @DisplayName("Should return null when bank code not found")
    void testFindByCodeNotFound() {
        Bank foundBank = bankRepository.findByCode("99999");

        assertNull(foundBank);
    }

    /**
     * Test Case 4: Delete bank from database
     * Expected: Bank is removed and no longer findable
     */
    @Test
    @DisplayName("Should delete bank from database")
    void testDeleteBank() {
        entityManager.persistAndFlush(testBank);
        bankRepository.deleteById("12345");

        var deletedBank = bankRepository.findById("12345");
        assertFalse(deletedBank.isPresent());
    }

    /**
     * Test Case 5: Update bank information
     * Expected: Bank name is updated successfully
     */
    @Test
    @DisplayName("Should update bank name successfully")
    void testUpdateBank() {
        entityManager.persistAndFlush(testBank);

        testBank.setBankName("Axis Bank Limited - Updated");
        Bank updatedBank = bankRepository.save(testBank);

        assertEquals("Axis Bank Limited - Updated", updatedBank.getBankName());
    }
}

