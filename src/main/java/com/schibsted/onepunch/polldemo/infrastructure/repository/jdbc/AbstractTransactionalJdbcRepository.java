package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc;

import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;

public abstract class AbstractTransactionalJdbcRepository {

    private final TransactionTemplate transactionTemplate;

    protected AbstractTransactionalJdbcRepository(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    protected Mono<Void> runnableTx(Runnable runnable) {
        return Mono.fromRunnable(() -> transactionTemplate.execute(transactionStatus -> {
            runnable.run();
            return Mono.empty();
        }));
    }

    protected <T> Mono<T> callableTx(Callable<T> callable) {
        return Mono.fromCallable(() -> transactionTemplate.execute(transactionStatus -> {
            try {
                return callable.call();
            } catch (Exception e) {
                throw new TransactionException(e);
            }
        }));
    }
}
