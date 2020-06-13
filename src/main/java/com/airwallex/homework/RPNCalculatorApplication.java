package com.airwallex.homework;

import com.airwallex.homework.controller.Calculator;

import java.util.Scanner;

public class RPNCalculatorApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() > 0) {
                String ret = calculator.calculator(line);
                System.out.println(ret);
            }
        }
    }
}
