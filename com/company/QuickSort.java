package com.company;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] A = new int[]{13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
        QuickSort q = new QuickSort();
        q.quickSort(A, 0, A.length-1);
        System.out.println(Arrays.toString(A));
    }

    void quickSort(int[] A, int p, int r){
        if(p<r){
            int q = partition(A, p, r);

            quickSort(A, p, q-1);
            quickSort(A, q+1, r);
        }
    }

    int partition(int[] A, int p, int r){

        int x = A[r];
        int i = p-1;

        for(int j=p; j<=r-1; j++){
            if(A[j]>=x){
                i++;
                swap(A, i, j);
            }
            System.out.println(Arrays.toString(A));
        }
//        System.out.println(Arrays.toString(A));
        swap(A, i+1, r);
        System.out.println(Arrays.toString(A));
        return i+1;
    }

    void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
