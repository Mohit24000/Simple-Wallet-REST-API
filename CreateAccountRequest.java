package com.learnSpringBoot.SimpleWallet.dto;

public record CreateAccountRequest(String owner, long balance)
{}
