package com.learnSpringBoot.SimpleWallet.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.learnSpringBoot.SimpleWallet.service.*.*(..)) || execution(* com.learnSpringBoot.SimpleWallet.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        String sig = pjp.getSignature().toShortString();
        long start = System.currentTimeMillis();
        try {
            System.out.println("➡ ENTER " + sig);
            return pjp.proceed();
        } finally {
            long time = System.currentTimeMillis() - start;
            System.out.println("⬅ EXIT  " + sig + " (took " + time + "ms)");
        }
    }
}
