package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HashMultDiv {

    public static final int m = 11;
    static List<List<Integer>> list = new ArrayList<>();
    static List<Integer> open = new ArrayList<>(Collections.nCopies(m, -1));
    public static void main(String[] args) {

//        int[] arr = new int[]{61, 62, 63, 64, 65};
//        for(int i=0; i<m; i++){
//            list.add(new ArrayList<>());
//        }
//
//        for(int i=0; i<arr.length; i++){
//            list.get(mulHash(arr[i])).add(arr[i]);
//        }
//
//        System.out.println(list.size());
//
//        for(int i=0; i<m; i++){
//            if(!list.get(i).isEmpty())
//                System.out.println(list.get(i).toString()+"========="+i);
//        }

        /**
         * open addressing
         */

        int[] openArr = new int[]{10, 22, 31, 4,15, 28, 17, 88, 59};

        for(int i=0;i<openArr.length; i++){
            open.set(doubleHash(openArr[i]), openArr[i]);
        }

        for(int i=0; i<m; i++){
            System.out.println(open.get(i));
        }
    }

    static int divHash(int k){
        return k%m;
    }

    static int mulHash(int k){
        double A = (Math.sqrt(5)-1)/2;
        return (int)Math.floor(m*((k*A)%1));
    }

    static int universalHash(int a, int b,int k, int p){
        return ((a*k+b)%p)%m;
    }

    static int linearProbe(int k){
        for(int i=0; i<m; i++){
            if(open.get((k+i)%m)==-1)
                return (k+i)%m;
        }
        return -1;
    }

    static int doubleHash(int k){
        for(int i=0; i<m; i++){
            if(open.get((k + i*(1+k%(m-1)))%m)==-1){
                return (k + i*(1+k%(m-1)))%m;
            }
        }
        return -1;
    }

    static int quadraticHash(int k){
        for(int i=0; i<m; i++){
            if(open.get((k+i + (3*i*i))%m)==-1){
//                System.out.println("ola");
                return (k+i + 3*(i*i))%m;
            }
        }
        return -1;
    }
}
