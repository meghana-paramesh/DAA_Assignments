package com.company;

public class Test {
    public static void main(String[] args) {

        int n=1;
        while(100*n*n>=Math.pow(2,n)){
            n++;
        }

        double i=2.0;
//        System.out.println(n);
        while (Math.pow(2, (i/8))<=i){
            ++i;
        }
        System.out.println(i);
    }
}
