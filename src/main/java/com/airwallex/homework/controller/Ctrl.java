package com.airwallex.homework.controller;

import com.airwallex.homework.entity.Pair;
import com.airwallex.homework.entity.UndoStack;

import java.math.BigDecimal;
import java.util.Deque;

public enum Ctrl {
    UNDO ("undo"){
        @Override
        public void execute(Deque<BigDecimal> stack, Deque<UndoStack> helpStack) {
            UndoStack undo = helpStack.pop();
            Pair original = undo.getOriginal();
            stack.pop();
            if (undo.getOperator() != null) {
                if (undo.getOperator().isTwoNumbers()) {
                    stack.push(original.getLeft());
                    stack.push(original.getRight());
                } else {
                    stack.push(original.getRight());
                }
            }
        }
    },
    CLEAR ("clear"){
        @Override
        public void execute(Deque<BigDecimal> stack, Deque<UndoStack> helpStack) {
            stack.clear();
            helpStack.clear();
        }
    };
    private String display;

    Ctrl(String display) {
        this.display = display;
    }

    public static Ctrl findCtrl(String str) {
        for (Ctrl c: Ctrl.values()) {
            if (c.display.equals(str)) {
                return c;
            }
        }
        return null;
    }

    public abstract void execute(Deque<BigDecimal> stack, Deque<UndoStack> helpStack);
}
