package com.airwallex.homework.controller;

import com.airwallex.homework.controller.Calculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    Calculator calculator;
    @Before
    public void init() {
        calculator = new Calculator();
    }
    @Test
    public void plain_number_no_error() {
        assertEquals("stack: 1 2 3", calculator.calculator("1 2 3"));
    }

    @Test()
    public void invalid_plain_number_error() {
        assertEquals("input k (position: 4): invalid parameters", calculator.calculator("1 k"));
    }

    @Test
    public void plain_add_no_error() {
        assertEquals("stack: 3", calculator.calculator("1 2 +"));
    }
    @Test
    public void plain_sub_no_error() {
        assertEquals("stack: -1", calculator.calculator("1 2 -"));
    }
    @Test
    public void plain_mul_no_error() {
        assertEquals("stack: 6", calculator.calculator("2 3 *"));
    }

    @Test
    public void plain_did_no_error() {
        assertEquals("stack: 0.75", calculator.calculator("3 4 /"));
    }

    @Test
    public void plain_sqrt_no_error() {
        assertEquals("stack: 3", calculator.calculator("9 sqrt"));
    }

    @Test
    public void combined_operation_no_error_case1() {
        assertEquals("stack: 6", calculator.calculator("9 sqrt 2 *"));
    }

    @Test
    public void combined_operation_no_error_case2() {
        assertEquals("stack: 1 11", calculator.calculator("1 3 4 2 * +"));
    }

    @Test
    public void simple_clear() {
        assertEquals("stack: ", calculator.calculator("1 3 4 2 * + clear"));
    }

    @Test
    public void simple_undo() {
        assertEquals("stack: 1 2", calculator.calculator("1 2 3 undo"));
    }

    @Test
    public void undo_case1() {
        assertEquals("stack: 1 2 3", calculator.calculator("1 2 3 * undo"));
    }

    @Test
    public void undo_case2() {
        assertEquals("stack: 1 2", calculator.calculator("1 2 3 * undo undo"));
    }

    @Test
    public void undo_case3() {
        assertEquals("stack: 1 2 3", calculator.calculator("1 2 3 * + undo undo"));
    }

    @Test
    public void undo_case4() {
        assertEquals("stack: 1 2 3", calculator.calculator("1 2 3 * + undo undo"));
        assertEquals("stack: 1 2", calculator.calculator("undo"));
        assertEquals("stack: 3", calculator.calculator("+"));
        assertEquals("stack: 27", calculator.calculator("4 5 + *"));
        assertEquals("stack: 3 4 5", calculator.calculator("undo undo"));
    }

    @Test
    public void insufficient_para() {
        assertEquals("operator * (position: 12): insufficient parameters", calculator.calculator("1 2 3 * + *"));
    }
}
