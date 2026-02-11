package com.wallet.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.request.WalletRequest;
import com.wallet.service.WalletService;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService service;

    @PostMapping
    public ResponseEntity<?> updateWallet(@RequestBody WalletRequest req) {
        return ResponseEntity.ok(service.process(req));
    }

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<?> getBalance(@PathVariable UUID walletId) {
        return ResponseEntity.ok(service.getBalance(walletId));
    }
}

