package com.company;

import java.util.Arrays;

public class LinearCongruentIntegralRandom {
    public static void main(String[] args) {
        System.out.println("ramdom numbers "+ Arrays.toString(generateRandomNumbers(5, 7, 3, 3, 5)));
    }

    private static int[] generateRandomNumbers(int x0, int m, int a, int c, int noOfRandNumbers){
        int[] randomNums = new int[noOfRandNumbers];
        randomNums[0] = x0;

        for(int i=1; i<noOfRandNumbers; i++){
            randomNums[i] = ((randomNums[i-1]*a)+c)/m;
        }
        return randomNums;
    }
}
