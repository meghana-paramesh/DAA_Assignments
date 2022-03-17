package com.company;

public class BruteForceMaxSubArray {
    public static void main(String[] args) {
        int[] arr = {-1, 4, -2, 7, -1};
        int start=0;
        int end=0;

        int sum=0, max=Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++){
            for(int j=i; j<arr.length; j++){
                sum+=arr[j];

                if(sum>max){
                    max=sum;
                    start=i;
                    end=j;
                }
            }
            sum=0;
        }
        System.out.println("start: "+start+" end: "+end+" max: "+max);
    }
}
