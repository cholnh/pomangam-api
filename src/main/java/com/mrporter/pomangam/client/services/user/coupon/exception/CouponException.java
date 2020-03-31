package com.mrporter.pomangam.client.services.user.coupon.exception;

public class CouponException extends RuntimeException {
    public CouponException() {
        super();
    }
    public CouponException(String message) {
        super(message);
    }
    public CouponException(String message, Throwable cause) {
        super(message, cause);
    }
    public CouponException(Throwable cause) {
        super(cause);
    }
}
