package com.wallet.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wallet")
@Getter
@Setter
public class Wallet {

    @Id
    @Column(name = "wallet_id", columnDefinition = "BINARY(16)")
    private UUID walletId;

    @Column(nullable = false)
    private long balance;

}

