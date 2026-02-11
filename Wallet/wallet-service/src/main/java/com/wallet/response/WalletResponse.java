package com.wallet.response;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletResponse {
    private UUID walletId;
    private long balance;
}
