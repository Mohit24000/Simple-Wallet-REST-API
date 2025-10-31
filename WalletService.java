package com.learnSpringBoot.SimpleWallet.service;

import com.learnSpringBoot.SimpleWallet.exception.InsufficientFundsException;
import com.learnSpringBoot.SimpleWallet.exception.NotFoundException;
import com.learnSpringBoot.SimpleWallet.model.Account;
import com.learnSpringBoot.SimpleWallet.notifier.Notifier;
import com.learnSpringBoot.SimpleWallet.repo.InMemoryAccountRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class WalletService {

    private final InMemoryAccountRepo repo;
    // notifier is optional (maybe absent if ConditionalOnProperty is false)
    private final Notifier notifier;

    @Autowired
    public WalletService(InMemoryAccountRepo repo, @Autowired(required = false) Notifier notifier) {
        this.repo = repo;
        this.notifier = notifier;
    }

    public Account createAccount(String owner, long balance) {
        String id = java.util.UUID.randomUUID().toString();
        Account a = new Account(id, owner, balance);
        repo.save(a);
        return a;
    }

    public Account getAccount(String id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Account not found: " + id));
    }

    /**
     * Transfer amount from fromId to toId.
     * This is an in-memory operation: we do simple concurrency-safe updates using repo.save.
     * Throws NotFoundException or InsufficientFundsException.
     */
    public void transfer(String fromId, String toId, long amount) {
        if (fromId.equals(toId)) {
            throw new IllegalArgumentException("fromId and toId cannot be the same");
        }
        Account from = repo.findById(fromId).orElseThrow(() -> new NotFoundException("From account not found: " + fromId));
        Account to = repo.findById(toId).orElseThrow(() -> new NotFoundException("To account not found: " + toId));

        synchronized (getLockForTwo(fromId, toId)) {
            if (from.getBalance() < amount) {
                throw new InsufficientFundsException("Insufficient funds in account: " + fromId);
            }
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            repo.save(from);
            repo.save(to);
        }

        if (notifier != null) {
            String msg = String.format("Transferred %d from %s to %s", amount, fromId, toId);
            notifier.notifyTransfer(msg);
        }
    }

    // Simple ordering lock to avoid deadlocks when synchronizing two accounts.
    private Object getLockForTwo(String a, String b) {
        // small deterministic lock object per pair - for demo only.
        // In production, use a robust locking strategy or DB transactions.
        return (a.compareTo(b) < 0) ? (a + "::" + b).intern() : (b + "::" + a).intern();
    }
}

