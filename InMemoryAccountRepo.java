package com.learnSpringBoot.SimpleWallet.repo;

import com.learnSpringBoot.SimpleWallet.model.Account;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryAccountRepo {
    private final Map<String, Account> store = new ConcurrentHashMap<>();

    public Account save(Account a) {
        store.put(a.getId(), a);
        return a;
    }

    public Optional<Account> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public boolean existsById(String id) {
        return store.containsKey(id);
    }
}
