package com.airwallex.homework.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class util {

    private static final int DISPLAY_SCALE = 10;
    private static final int STORE_SCALE = 15;

    private static List<BigDecimal> convertToList(Deque<BigDecimal> stack) {
        List<BigDecimal> ret = new ArrayList<>(stack);
        Collections.reverse(ret);
        return ret;
    }

    public static String printStack(Deque<BigDecimal> stack) {
        return String.format("stack: %s",
                convertToList(stack).stream().map(i -> display(i)).collect(Collectors.joining(" ")));
    }

    private static String display(BigDecimal a) {
        return new BigDecimal(a.toPlainString()).setScale(DISPLAY_SCALE, BigDecimal.ROUND_CEILING).stripTrailingZeros().toPlainString();
    }

    public static BigDecimal tryParseBigDecimal(String str) {
        try {
            return new BigDecimal(str, MathContext.DECIMAL64).setScale(STORE_SCALE, RoundingMode.FLOOR);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
