package com.wissen.practice;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalR {

    public static void main(String[] args) {
        int precision = 25;
        int scale = 6;
        String[] numbers = {"12345.12345467", "12345.123456","12345.12345067","12345.1234597"};
        String[] expectedNumbers = {"12345.12345", "12345.12346"};

        for (int i = 0; i < numbers.length; i++) {
            BigDecimal number = new BigDecimal(numbers[i]);
          
            BigDecimal roundedNumber = number.setScale(scale, RoundingMode.HALF_UP);

            System.out.println("Rounded Number: " + roundedNumber);

 /*          BigDecimal expectedNumber = new BigDecimal(expectedNumbers[i]);
            int comparisonResult = roundedNumber.compareTo(expectedNumber);
            if (comparisonResult == 0) {
                System.out.println("The rounded number matches the expected number.");
            } else if (comparisonResult < 0) {
                System.out.println("The rounded number is less than the expected number.");
            } else {
                System.out.println("The rounded number is greater than the expected number.");
            }*/
        }
    }

}
