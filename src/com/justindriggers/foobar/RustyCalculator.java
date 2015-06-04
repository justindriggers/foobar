package com.justindriggers.foobar;

public class RustyCalculator {

    public static void main(String[] args) {
        System.out.println(answer("2+3*2"));
        System.out.println(answer("2*4*3+9*3+5"));
    }

    public static String answer(String str) {
        StringBuilder result = new StringBuilder();

        String[] additionSegments = str.split("\\+");
        StringBuilder additionSymbolBuilder = new StringBuilder();

        for (int i = 0; i < additionSegments.length; i++) {
            String[] multiplicationSegments = additionSegments[i].split("\\*");
            StringBuilder multiplicationSymbolBuilder = new StringBuilder();

            for (int j = 0; j < multiplicationSegments.length; j++) {
                result.append(multiplicationSegments[j]);

                if (j < multiplicationSegments.length - 1) {
                    multiplicationSymbolBuilder.append('*');
                }
            }

            result.append(multiplicationSymbolBuilder);

            if (i < additionSegments.length - 1) {
                additionSymbolBuilder.append('+');
            }
        }

        result.append(additionSymbolBuilder);

        return result.toString();
    }
}
