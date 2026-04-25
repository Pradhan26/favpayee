package com.bankapplication.bank.service;

import com.bankapplication.bank.dto.BankCodeResponseDto;
import com.bankapplication.bank.dto.BankRequest;
import com.bankapplication.bank.entity.Bank;
import com.bankapplication.bank.repository.BankRepository;
import com.bankapplication.exception.BankException;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    private final BankRepository bankRepository;

     BankService(BankRepository repository){
        this.bankRepository = repository;
    }

    public BankCodeResponseDto getBankByCode(String code) {
        if (code == null) {
            throw new BankException("Invalid bank code");
        }
        Bank bank = bankRepository.findByCode(code);
        if (bank == null) {
            throw new BankException("Bank not found");
        }
        return map(bank);
    }


    public BankCodeResponseDto createBank(BankRequest bankRequest){
         Bank bank = this.bankRepository.save(new Bank(bankRequest.getCode(), bankRequest.getBankName()));
         return new BankCodeResponseDto( bank.getCode(),bank.getBankName());
    }



    private BankCodeResponseDto map(Bank bank){
         return new BankCodeResponseDto(bank.getCode(), bank.getBankName());
    }
}
