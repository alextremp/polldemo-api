package com.schibsted.onepunch.polldemo.infrastructure.repository.jdbc;

public class TransactionException extends RuntimeException {
    public TransactionException(Throwable cause) {
        super(cause);
    }
}
