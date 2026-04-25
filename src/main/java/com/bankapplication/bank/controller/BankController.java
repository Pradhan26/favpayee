package com.bankapplication.bank.controller;

import com.bankapplication.bank.dto.BankCodeResponseDto;
import com.bankapplication.bank.dto.BankRequest;
import com.bankapplication.bank.service.BankService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    private final BankService bankService;

    BankController(BankService service){
        this.bankService = service;
    }

    @GetMapping("/{bankCode}")
    public ResponseEntity<BankCodeResponseDto> getBankByCode(@PathVariable String bankCode) {
        return ResponseEntity.ok(this.bankService.getBankByCode(bankCode));
    }

    @PostMapping()
    public ResponseEntity<BankCodeResponseDto> addBank(@Valid @RequestBody BankRequest bankRequest) {
        return ResponseEntity.ok(this.bankService.createBank(bankRequest));
    }
}
