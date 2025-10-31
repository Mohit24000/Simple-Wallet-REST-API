# Simple-Wallet-REST-API
SimpleWallet is a Spring Boot–based web application that simulates a lightweight digital wallet system.
It enables users to create accounts, transfer funds, and handle exceptions in a structured, modular, and asynchronous manner.
The project demonstrates best practices in Spring Boot architecture, including layered design, aspect-oriented programming (AOP), exception handling, and configuration management.
src
└── main
    └── java
        └── com.learnSpringBoot.SimpleWallet
            ├── aspect
            │   └── LoggingAspect
            │
            ├── config
            │   ├── CorrelationInterceptor
            │   └── WebConfig
            │
            ├── controller
            │   └── WalletController
            │
            ├── dto
            │   ├── CreateAccountRequest
            │   └── TransferRequest
            │
            ├── exception
            │   ├── GlobalExceptionHandler
            │   ├── InsufficientFundsException
            │   └── NotFoundException
            │
            ├── model
            │   └── Account
            │
            ├── notifier
            │   ├── AsyncNotifier
            │   └── Notifier
            │
            ├── repo
            │   └── InMemoryAccountRepo
            │
            ├── service
            │   └── WalletService
            │
            └── SimpleWalletApplication
