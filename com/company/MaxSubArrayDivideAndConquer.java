package com.company;

public class MaxSubArrayDivideAndConquer {
    public static void main(String[] args) {
        int[] arr = {31, -55, -57, -39, 23, 91, -28, -11, -68, -43, 0, 16, 69, 18, 39, -58, -27, -26, 21, -63};
        int[] res = findMaxSubArray(arr, 0, arr.length-1);
        System.out.println("start: "+res[0]+" end: "+res[1]+" max: "+res[2]);
    }

    public static int[] findMaxSubArray(int[] arr, int low, int high){
        if(low==high)
            return new int[]{low,high,arr[low]};
        else{
            int mid = low+(high-low)/2;
            int[] left = findMaxSubArray(arr, low, mid);
            int[] right = findMaxSubArray(arr, mid+1, high);
            int[] cross = findMaxCrossingSubArray(arr, low, mid, high);

            if(left[2]>right[2] && left[2]>cross[2])
                return left;
            else if(right[2]>left[2] && right[2]>cross[2])
                return right;
            else
                return cross;
        }
    }

    private static int[] findMaxCrossingSubArray(int[] arr, int low, int mid, int high){
        int leftSum = Integer.MIN_VALUE, leftMax=0;
        int sum=0;

        for(int i=mid; i>=low; i--){
            sum = sum+arr[i];
            if(sum>leftSum){
                leftSum = sum;
                leftMax = i;
            }
        }

        int rightSum = Integer.MIN_VALUE, rightMax=0;
        sum=0;

        for(int i=mid+1; i<=high; i++){
            sum = sum+arr[i];
            if(sum>rightSum){
                rightSum = sum;
                rightMax = i;
            }
        }

        return new int[]{leftMax, rightMax, leftSum+rightSum};
    }
}
