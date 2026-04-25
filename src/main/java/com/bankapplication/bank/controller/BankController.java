package com.bankapplication.bank.controller;

import com.bankapplication.bank.dto.BankCodeResponseDto;
import com.bankapplication.bank.service.BankService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    private final BankService bankService;

    BankController(BankService service){
        this.bankService = service;
    }

    @GetMapping("/{bankCode}")
    public ResponseEntity<BankCodeResponseDto> getBankByCode(@PathParam("bankCode") int bankCode) {
        try {
            return ResponseEntity.ok(this.bankService.getBankByCode(bankCode));
        }
        catch (IllegalArgumentException e){
            //custom
            return ResponseEntity.badRequest().build();
        }
    }

}
