package com.company;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] mergeArr = {29, 31, 19, 72, 59, 12, 28, 63};
        mergeSort(mergeArr, 0, mergeArr.length-1);
        System.out.println("Merge sorted: "+ Arrays.toString(mergeArr));
    }

    private static void mergeSort(int[] arr, int low, int high){


        if(low<high){
            int mid = low+(high-low)/2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high){

        int n1 = mid-low+1;
        int n2 = high-mid;

        int left[] = new int[n1];
        int right[] = new int[n2];

        for(int i=0; i<n1; ++i){
            left[i] = arr[low+i];
        }

        for(int j=0; j<n2; ++j){
            right[j] = arr[mid+1+j];
        }

        int i=0, j=0, k=low;

        while (i<n1 && j<n2){

            if(left[i]>right[j]){
                arr[k] = right[j];
                j++;
            }

            else{
                arr[k] = left[i];
                i++;
            }
            k++;
            System.out.println("array: "+Arrays.toString(arr));
        }

        while(i<n1){
            arr[k] = left[i];
            i++;
            k++;
            System.out.println("arr: "+Arrays.toString(arr));
        }

        while(j<n2){
            arr[k] = right[j];
            j++;
            k++;
            System.out.println("arr: "+Arrays.toString(arr));
        }
    }
}
