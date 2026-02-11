package com.wallet.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.wallet.request.WalletRequest;
import com.wallet.response.WalletResponse;

@Service
public interface WalletService {

	WalletResponse process(WalletRequest req);
	
	WalletResponse getBalance(UUID walletId);
	
}
