package ru.msaitov.practice.controller.handleException;

/**
 * Custom Exception
 */
public class CustomNotFoundException extends RuntimeException {

    /**
     * @param msg
     */
    public CustomNotFoundException(String msg) {
        super(msg);
    }
}
