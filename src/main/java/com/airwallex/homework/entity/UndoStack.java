package com.airwallex.homework.entity;

import com.airwallex.homework.controller.Operator;

import java.math.BigDecimal;

public class UndoStack {
    BigDecimal value;
    Pair original;
    Operator operator;
    public UndoStack(BigDecimal value, Pair original, Operator operator) {
        this.value = value;
        this.original = original;
        this.operator = operator;
    }

    public Pair getOriginal() {
        return this.original;
    }

    public Operator getOperator() {
        return this.operator;
    }
}
