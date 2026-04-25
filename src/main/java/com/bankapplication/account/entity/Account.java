package com.bankapplication.account.entity;


import com.bankapplication.auth.entity.Customer;
import com.bankapplication.bank.entity.Bank;
import jakarta.persistence.*;

@Entity
@Table(
        name = "favorite_accounts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"customer_id", "iban_number"})
        },
        indexes = {
                @Index(name = "idx_customer_id", columnList = "customer_id")
        }
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "account_name", nullable = false, length = 100)
    private String accountName;

    @Column(name = "iban_number", nullable = false, length = 34)
    private String ibanNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_code", referencedColumnName = "code", nullable = false)
    private Bank bank;

    public Account() {}

    public Account(String accountName, String ibanNumber,
                   Customer customer, Bank bank) {
        this.accountName = accountName;
        this.ibanNumber = ibanNumber;
        this.customer = customer;
        this.bank = bank;
    }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getIbanNumber() { return ibanNumber; }
    public void setIbanNumber(String ibanNumber) { this.ibanNumber = ibanNumber; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Bank getBank() { return bank; }
    public void setBank(Bank bank) { this.bank = bank; }
}