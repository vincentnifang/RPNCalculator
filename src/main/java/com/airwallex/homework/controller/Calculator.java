package com.airwallex.homework.controller;

import com.airwallex.homework.entity.UndoStack;
import com.airwallex.homework.exception.CalculatorException;
import com.airwallex.homework.exception.InvalidInputException;
import com.airwallex.homework.util.util;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;


public class Calculator {

    private Deque<BigDecimal> stack = new ArrayDeque<BigDecimal>();
    private Deque<UndoStack> helpStack = new ArrayDeque <UndoStack>();
    private int pos = 0;

    private void process(String str, int pos) throws CalculatorException {
        Ctrl c = Ctrl.findCtrl(str);
        Operator o = Operator.findOperator(str);
        BigDecimal value = util.tryParseBigDecimal(str);
        if (value != null) {
            simplePush(value);
        } else if (c != null) {
            c.execute(stack, helpStack);
        } else if (o != null) {
            o.execute(stack, helpStack, pos);
        } else {
            throw new InvalidInputException(str, pos);
        }
    }

    public void simplePush(BigDecimal value) {
        stack.push(value);
        UndoStack last = new UndoStack(value, null, null);
        helpStack.push(last);
    }

    public String calculator(String line) {
        try {
            return cal(line);
        } catch (CalculatorException e) {
            return e.getMessage();
        }
    }

    private String cal(String line) throws CalculatorException {
        if (line == null) {
            throw new CalculatorException("Input cannot be null.");
        }
        pos = 0;
        String[] result = line.split("\\s");
        for (String str : result) {
           pos += str.length() + 1;
           process(str, pos);
        }
        return util.printStack(stack);
    }

}
