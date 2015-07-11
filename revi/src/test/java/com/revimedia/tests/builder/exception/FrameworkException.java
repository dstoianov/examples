package com.revimedia.tests.builder.exception;

public class FrameworkException extends RuntimeException {

    private static final long serialVersionUID = 1049961585967770198L;

    public FrameworkException() {
        super();
    }

    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public FrameworkException(Throwable throwable) {
        super(throwable);
    }

}
