package com.airwallex.homework.exception;

public class InsufficientParameterException extends CalculatorException {
    public InsufficientParameterException(String display, int pos) {
        super(String.format("operator %s (position: %d): insufficient parameters", display, pos));
    }
}
