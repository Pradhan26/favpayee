package com.bankapplication.bank.service;

import com.bankapplication.bank.dto.BankCodeResponseDto;
import com.bankapplication.bank.entity.Bank;
import com.bankapplication.bank.repository.BankRepository;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    private BankRepository bankRepository;

     BankService(BankRepository repository){
        this.bankRepository = repository;
    }

    public BankCodeResponseDto getBankByCode(int code){
         if(code == 0){
            throw new IllegalArgumentException("Invalid bank code");
         }
       Bank bank=  bankRepository.findByCode(code);
         return map(bank);
    }

    private BankCodeResponseDto map(Bank bank){
         return new BankCodeResponseDto(bank.bankName, bank.code);
    }
}
