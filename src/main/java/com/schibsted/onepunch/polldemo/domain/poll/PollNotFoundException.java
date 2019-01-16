package com.schibsted.onepunch.polldemo.domain.poll;

public class PollNotFoundException extends RuntimeException {
    public PollNotFoundException(String message) {
        super(message);
    }
}
