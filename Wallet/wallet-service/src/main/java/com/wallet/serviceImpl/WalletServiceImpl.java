package com.wallet.serviceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.entity.Wallet;
import com.wallet.repository.WalletRepository;
import com.wallet.request.WalletRequest;
import com.wallet.response.WalletResponse;
import com.wallet.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

	
	 @Autowired
	    private WalletRepository repo;
	
	@Override
	@Transactional
	public WalletResponse process(WalletRequest req) {
		
		createWallet(req.getWalletId());  //		create wallet for 0 balance 
		
        Wallet wallet = repo.findByIdForUpdate(req.getWalletId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (req.getAmount() <= 0) {
            throw new RuntimeException("Invalid amount");
        }

        if ("DEPOSIT".equals(req.getOperationType())) {
            wallet.setBalance(wallet.getBalance() + req.getAmount());
        }
        else if ("WITHDRAW".equals(req.getOperationType())) {

            if (wallet.getBalance() < req.getAmount()) {
                throw new RuntimeException("Insufficient funds");
            }

            wallet.setBalance(wallet.getBalance() - req.getAmount());
        } else {
            throw new RuntimeException("Invalid operation");
        }

        repo.save(wallet);

        WalletResponse res = new WalletResponse();
        res.setWalletId(wallet.getWalletId());
        res.setBalance(wallet.getBalance());
        return res;
    }

	@Override
	public WalletResponse getBalance(UUID walletId) {
        Wallet wallet = repo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        WalletResponse res = new WalletResponse();
        res.setWalletId(walletId);
        res.setBalance(wallet.getBalance());
        return res;
    }
	
	@Transactional
	public void createWallet(UUID walletId) {
	    if (repo.existsById(walletId)) {
	        throw new RuntimeException("Wallet already exists");
	    }

	    Wallet wallet = new Wallet();
	    wallet.setWalletId(walletId);
	    wallet.setBalance(0);

	    repo.save(wallet);
	}


}
