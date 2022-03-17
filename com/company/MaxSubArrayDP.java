package com.company;

public class MaxSubArrayDP {
    public static void main(String[] args) {
        int[] arr = {1, 4, -2, -1, 8};

        int max=arr[0], max_so_far=arr[0];
        int start=0, end=0;
        for(int i=1; i<arr.length; i++){

            if(max_so_far+arr[i]<arr[i]){
                start=i;
                max_so_far=arr[i];
            }else{
                max_so_far=max_so_far+arr[i];
            }
//            max_so_far = Math.max(max_so_far+arr[i], arr[i]);
//            max = Math.max(max_so_far, max);

            if(max_so_far>max){
                end=i;
                max=max_so_far;
            }
        }
        System.out.println("start: "+start+" end: "+end+" max: "+max);
    }
}
