package com.wallet.request;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletRequest {
    private UUID walletId;
    private String operationType; // DEPOSIT or WITHDRAW
    private long amount;
}
