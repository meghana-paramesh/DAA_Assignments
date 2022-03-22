package com.company;

import java.util.Arrays;

public class CoinCollection {
    public static void main(String[] args) {
        int[][] c = {{0,0,0,0,1,0},
                {0,1,0,1,0,0},
                {0,0,0,1,0,1},
                {0,0,1,0,0,1},
                {1,0,0,0,1,0}};
        int[][] f = maxCoins(c);

        for(int i=0; i<f.length; i++){
            System.out.println(Arrays.toString(f[i]));
        }
    }

    static private int[][] maxCoins(int[][] c){
        int n = c.length;
        int m = c[0].length;

        int[][] f = new int[n+1][m+1];
        f[1][1] = c[1][1];

        for(int j=1; j<=m; j++)
            f[1][j] = f[1][j-1]+c[0][j-1];
//
        for(int i=1; i<=n; i++){
            f[i][1] = f[i-1][1]+c[i-1][0];

            for(int j=1; j<=m; j++){
                f[i][j] = Math.max(f[i-1][j], f[i][j-1]+c[i-1][j-1]);
            }
        }
        System.out.println(f[n][m]);
        return f;
    }
}
