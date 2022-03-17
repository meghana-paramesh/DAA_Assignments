package com.company;

import java.util.Arrays;

public class RobotCoinCollection {
    public static void main(String[] args) {
        int[][] C = new int[][]{{0, 0, 0, 0, 1, 0},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 1, 0}};
        System.out.println("max coins: "+maxCoinsCollected(C));
    }

    private static int maxCoinsCollected(int[][] C){
        int n = C.length;
        int m = C[0].length;

        int[][] F = new int[n][m];
        F[0][0] = C[0][0];

        for(int j=1; j<m; j++){
            F[0][j] = F[0][j-1]+C[0][j];
        }

        for(int i=1; i<n; i++){
            F[i][0] = F[i-1][0]+C[i][0];
            for(int j=1; j<m; j++){
                F[i][j] = Math.max(F[i-1][j], F[i][j-1])+C[i][j];
            }
        }

        int row=n-1, col=m-1;
        while(row>=1 && col>=1){
            if(F[row-1][col]>F[row][col-1]){
                System.out.println("path: "+(row-1+1)+" "+(col+1));
                row=row-1;
            }else{
                System.out.println("path: "+(row+1)+" "+(col-1+1));
                col=col-1;
            }
        }
        return F[n-1][m-1];
    }

    private static int[] parent(int i,int j, int[][] F){
        if(F[i-1][j]>F[i][j-1])
            return new int[]{i-1, j};
        return new int[]{i, j-1};
    }
}
