package com.company;

import java.util.Arrays;

/*
INSERTION-SORT(A)
 1  for j = 2 to A.length do
 2      key = A[j]
 3      // Insert A[j] into the sorted sequence A[1 .. j – 1]
 4      i = j – 1
 5      while i > 0 and A[i] > key do
 6          A[i + 1] = A[i]
 7          i = i – 1
 8      A[i + 1] = key
 */

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {96, 72, 61, 3, 93, 90, 38, 84};
        System.out.println("sorted: "+ Arrays.toString(InsertionSort.insertionSort(arr)));
    }

    private static int[] insertionSort(int[] arr){
        int n=arr.length;
        for(int j=1; j<n; j++){
            int key = arr[j];

            int i=j-1;
            while (i>=0 && arr[i]>key){
                arr[i+1] = arr[i];
                i--;
            }
            arr[i+1] = key;
            System.out.println("j: "+j+" Array: "+Arrays.toString(arr));
        }
        return arr;
    }
}
