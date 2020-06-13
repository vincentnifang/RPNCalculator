package com.airwallex.homework.exception;

public class InvalidInputException extends CalculatorException  {
    public InvalidInputException(String str, int pos) {
        super(String.format("input %s (position: %d): invalid parameters", str, pos));
    }
}
