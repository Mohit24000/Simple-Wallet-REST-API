package com.learnSpringBoot.SimpleWallet.dto;

public record TransferRequest(String fromId, String toId, long amount){}
