package com.mrporter.pomangam._bases.securities.service.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthCodeNotFoundException extends AuthenticationException {
    public AuthCodeNotFoundException(String msg) {
        super(msg);
    }
    public AuthCodeNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
