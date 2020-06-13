package com.airwallex.homework.entity;

import java.math.BigDecimal;

public class Pair {

    private final BigDecimal left;
    private final BigDecimal right;

    public Pair(BigDecimal left, BigDecimal right) {
        this.left = left;
        this.right = right;
    }

    public BigDecimal getLeft() { return left; }
    public BigDecimal getRight() { return right; }

    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair pairo = (Pair) o;
        return this.left.equals(pairo.getLeft()) &&
                this.right.equals(pairo.getRight());
    }

}