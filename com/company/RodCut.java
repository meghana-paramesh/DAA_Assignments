package com.company;

import java.util.Arrays;

public class RodCut {
    public static void main(String[] args) {
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        for(int i=0; i<=prices.length; i++){
            System.out.println("size: "+i);
            System.out.println(Arrays.toString(cutRod(prices, i)));

            System.out.println("=====================================");
        }

    }

    static int[] cutRod(int price[], int n)
    {
        int val[] = new int[n + 1];
        int[] s = new int[n+1];
        val[0] = 0;

        // Build the table val[] in bottom up manner and return
        // the last entry from the table
        for (int i = 1; i <= n; i++) {
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++)
                if(max_val<price[j] + val[i - j - 1]){
                    max_val = price[j] + val[i - j - 1];
                    s[i] = j+1;
                }

            val[i] = max_val;
        }

//        int x=n;
//        n=n-1;
//
//        while(n>=0 && s[n]==0){
//            n--;
//        }
        System.out.println("pieces: ");
        while(n>0){
            System.out.print(s[n]+" ");
            n = n-s[n];
        }
        System.out.println();
        return val;
    }
}
