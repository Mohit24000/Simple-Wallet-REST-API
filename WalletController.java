package com.learnSpringBoot.SimpleWallet.controller;

import com.learnSpringBoot.SimpleWallet.dto.CreateAccountRequest;
import com.learnSpringBoot.SimpleWallet.dto.TransferRequest;
import com.learnSpringBoot.SimpleWallet.model.Account;
import com.learnSpringBoot.SimpleWallet.repo.InMemoryAccountRepo;
import com.learnSpringBoot.SimpleWallet.service.WalletService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;
    private final InMemoryAccountRepo repo;

    public WalletController(WalletService walletService, InMemoryAccountRepo repo) {
        this.walletService = walletService;
        this.repo = repo;
    }

    // Create new account
    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest req) {
        Account acc = walletService.createAccount(req.owner(), req.balance());
        return ResponseEntity.status(HttpStatus.CREATED).body(acc);
    }

    // Get account by ID
    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getAccount(@PathVariable String id) {
        return repo.findById(id)
                .map(acc -> ResponseEntity.<Object>ok(acc))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Account not found: " + id)));
    }


    // Transfer money
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest req) {
        walletService.transfer(req.fromId(), req.toId(), req.amount());
        return ResponseEntity.ok(java.util.Map.of("status", "Transfer successful"));
    }
}
