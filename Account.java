package com.learnSpringBoot.SimpleWallet.model;

public class Account {
    private final String id;
    private String owner;
    private long balance; // smallest unit (e.g., paise) to avoid floating point

    public Account(String id, String owner, long balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
    }

    public String getId() { return id; }
    public long getBalance() { return balance; }
    public void setBalance(long balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Account{" + "id='" + id + '\'' + ", owner='" + owner + '\'' + ", balance=" + balance + '}';
    }
}

