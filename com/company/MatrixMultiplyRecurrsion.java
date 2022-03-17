package com.company;

public class MatrixMultiplyRecurrsion {

    static int numMultiplications=0;
    static int numAdditions=0;
    static int numOfSubs=0;
    public static void main(String[] args)
    {
        // Display message
        System.out.println(
                "Strassen Multiplication Algorithm Implementation For Matrix Multiplication :\n");

        // Create an object of Strassen class
        // in he main function
        MatrixMultiplyRecurrsion s = new MatrixMultiplyRecurrsion();

        // Size of matrix
        // Considering size as 4 in order to illustrate
        int N = 4;

        // Matrix A
        // Custom input to matrix
        int[][] A = { { 1, 2, 3, 4 },
                { 4, 3, 0, 1 },
                { 5, 6, 1, 1 },
                { 0, 2, 5, 6 } };

        // Matrix B
        // Custom input to matrix
        int[][] B = { { 1, 0, 5, 1 },
                { 1, 2, 0, 2 },
                { 0, 3, 2, 3 },
                { 1, 2, 1, 2 } };

        // Matrix C computations

        // Matrix C calling method to get Result
        int[][] C = s.recurrsiveCallMul(A, B);

        // Display message
        System.out.println(
                "\nProduct of matrices A and  B : ");

        // Iterating over elements of 2D matrix
        // using nested for loops

        System.out.println("Normal recurrsion:");
        // Outer loop for rows
        for (int i = 0; i < N; i++) {
            // Inner loop for columns
            for (int j = 0; j < N; j++)

                // Printing elements of resultant matrix
                // with whitespaces in between
                System.out.print(C[i][j] + " ");

            // New line once the all elements
            // are printed for specific row
            System.out.println();
        }
        System.out.println("number of additions: "+numAdditions);
        System.out.println("number of subs: "+numOfSubs);
        System.out.println("number of muls: "+numMultiplications);
    }


    private int[][] recurrsiveCallMul(int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];

        if(n==1){
            C[0][0] = A[0][0]*B[0][0];
            numMultiplications++;
        }else{
            int[][] A11 = new int[n/2][n/2];
            int[][] A12 = new int[n/2][n/2];
            int[][] A21 = new int[n/2][n/2];
            int[][] A22 = new int[n/2][n/2];
            int[][] B11 = new int[n/2][n/2];
            int[][] B12 = new int[n/2][n/2];
            int[][] B21 = new int[n/2][n/2];
            int[][] B22 = new int[n/2][n/2];

            split(A, A11, 0,0);
            split(A, A12, 0, n/2);
            split(A, A21, n/2, 0);
            split(A, A22, n/2, n/2);

            split(B, B11, 0,0);
            split(B, B12, 0, n/2);
            split(B, B21, n/2, 0);
            split(B, B22, n/2, n/2);

            int[][] C11 = add(recurrsiveCallMul(A11, B11),recurrsiveCallMul(A12, B21));
            int[][] C12 = add(recurrsiveCallMul(A11, B12),recurrsiveCallMul(A12, B22));
            int[][] C21 = add(recurrsiveCallMul(A21, B11),recurrsiveCallMul(A22, B21));
            int[][] C22 = add(recurrsiveCallMul(A21, B12),recurrsiveCallMul(A22, B22));

            join(C11, C, 0, 0);
            join(C12, C, 0, n/2);
            join(C21, C, n/2, 0);
            join(C22, C, n/2, n/2);
        }
        return C;
    }

    public int[][] add(int[][] A, int[][] B)
    {

        //
        int n = A.length;

        // Creating a 2D square matrix
        int[][] C = new int[n][n];

        // Iterating over elements of 2D matrix
        // using nested for loops

        // Outer loop for rows
        for (int i = 0; i < n; i++)

            // Inner loop for columns
            for (int j = 0; j < n; j++){
                // Adding corresponding elements
                // of matrices
                C[i][j] = A[i][j] + B[i][j];
                numAdditions++;
            }



        // Returning the resultant matrix
        return C;
    }

    // Method 4
    // Function to split parent matrix
    // into child matrices
    public void split(int[][] Full, int[][] Part, int iB, int jB)
    {
        // Iterating over elements of 2D matrix
        // using nested for loops

        // Outer loop for rows
        for (int i1 = 0, i2 = iB; i1 < Part.length; i1++, i2++)

            // Inner loop for columns
            for (int j1 = 0, j2 = jB; j1 < Part.length;
                 j1++, j2++)

                Part[i1][j1] = Full[i2][j2];
    }

    // Method 5
    // Function to join child matrices
    // into (to) parent matrix
    public void join(int[][] C, int[][] P, int iB, int jB)

    {
        // Iterating over elements of 2D matrix
        // using nested for loops

        // Outer loop for rows
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)

            // Inner loop for columns
            for (int j1 = 0, j2 = jB; j1 < C.length;
                 j1++, j2++)

                P[i2][j2] = C[i1][j1];
    }
}
