package com.learnSpringBoot.SimpleWallet.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String msg) { super(msg); }
}
