package com.airwallex.homework.controller;

import com.airwallex.homework.entity.Pair;
import com.airwallex.homework.entity.UndoStack;
import com.airwallex.homework.exception.CalculatorException;
import com.airwallex.homework.exception.InsufficientParameterException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Deque;

public enum Operator {
    ADDITION ("+", true){
        @Override
        public BigDecimal oper(BigDecimal a, BigDecimal b) {
            return a.add(b);
        }
    },
    SUBTRACTION ("-", true){
        @Override
        public BigDecimal oper(BigDecimal a, BigDecimal b) {
            return a.subtract(b);
        }
    },
    MULTIPLICATION ("*", true){
        @Override
        public BigDecimal oper(BigDecimal a, BigDecimal b) {
            return a.multiply(b);
        }
    },
    DIVISION ("/", true){
        @Override
        public BigDecimal oper(BigDecimal a, BigDecimal b) {
            return a.divide(b, MathContext.DECIMAL64);
        }
    },
    SQRT ("sqrt", false) {
        @Override
        public BigDecimal oper(BigDecimal a, BigDecimal b) {
            return new BigDecimal(Math.sqrt(b.doubleValue()));
        }
    };

    private String display;
    private boolean isTwoNumbers;

    Operator(String display, boolean isTwoNumbers) {
        this.isTwoNumbers = isTwoNumbers;
        this.display = display;
    }

    public boolean isTwoNumbers() {
        return isTwoNumbers;
    }
    public abstract BigDecimal oper(BigDecimal a, BigDecimal  b);

    public void execute(Deque<BigDecimal> stack, Deque<UndoStack> helpStack, int pos) throws CalculatorException {

        if (stack.isEmpty() || (this.isTwoNumbers()? stack.size() < 2 : false)) {
            throw new InsufficientParameterException(this.display, pos);
        }
        try {
            // get
            BigDecimal r = stack.pop();
            BigDecimal l = this.isTwoNumbers() ? stack.pop() : null;
            // calculate
            BigDecimal v = this.oper(l, r);
            // push back log
            helpStack.push(new UndoStack(v, new Pair(l,r), this));
            stack.push(v);
        } catch (ArithmeticException e) {
            //TODO: no requirement so far
            throw new CalculatorException("ArithmeticException error");
        }

    }

    public static Operator findOperator(String str) {
        for (Operator o: Operator.values()) {
            if (o.display.equals(str)) {
                return o;
            }
        }
        return null;
    }

}
