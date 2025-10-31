package com.learnSpringBoot.SimpleWallet.notifier;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "notifier.enabled", havingValue = "true")
public class AsyncNotifier implements Notifier {

    @Async
    @Override
    public void notifyTransfer(String message) {
        try {
            // simulate async work delay
            Thread.sleep(700);
        } catch (InterruptedException ignored) {}
        System.out.println("[AsyncNotifier] " + message);
    }
}

