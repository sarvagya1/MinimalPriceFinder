package org.example.exception;

public class InvalidArgumentException extends Exception {
    public InvalidArgumentException(String invalidArgument) {
        super(invalidArgument);
    }
}
