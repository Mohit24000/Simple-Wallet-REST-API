package com.learnSpringBoot.SimpleWallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SimpleWalletApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleWalletApplication.class, args);
    }
}
