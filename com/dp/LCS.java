package com.dp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

/**
 * @project CS 617
 * @program LCS
 * @author Meghana Nagarahalli Paramesh
 * @purpose Implementation of longest common subsequence (LCS) dynamic programming algorithm.
 *
 * Instructions to run the program:
 * Step 1: javac LCS.java
 * Step 2: java LCS
 */
public class LCS {

    static BufferedWriter writer;

    /*
    Static block to initialize the BufferedWriter so that it can be used throughout the class
     */
    static {
        try {
            writer = new BufferedWriter(new FileWriter("output1.txt"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /*
    Utility class for writing into output file
     */
     static OutputFileWriter outputFileWriter = new OutputFileWriter();

    public static void main(String[] args) {

        /*
        Read the input file from the same resource path as the source code file
         */
        URL path = LCS.class.getResource("input.txt");
        File file = new File(path.getFile());

        /*
        Initializing a bufferedReader to read from the file.
        Catch proper exceptions if error occurs while reading from file or writing into the file
         */
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String line;

            while((line = bufferedReader.readLine()) != null){
                String[] tokens = line.split(",");
                LCS lcs = new LCS();
                TableBAndC tableBAndC = lcs.LCSLength(tokens[0].toCharArray(), tokens[1].toCharArray());
                int[][] c = tableBAndC.c;
                Directions[][] b = tableBAndC.b;
                StringBuilder sb = (lcs.reconstructLCS(b, tokens[0].toCharArray(), tokens[0].length(), tokens[1].length(), new StringBuilder()));
                outputFileWriter.writeToAFile(writer, sb.toString()+"\n");
            }

            /*
            Close the writer object to avoid buffer overflow
             */
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Function from [Cormen, 2009]
    @param X input sequence
           Y input sequence
    @return both table b and c

    @description Find LCS length by filling in tables b and c.
                 Based on LCS-LENGTH pseudocode, [Cormen, 2009] p. 394.
     */
    TableBAndC LCSLength(char[] X, char[] Y){
        int m = X.length;
        int n = Y.length;

        Directions[][] b = new Directions[m+1][n+1];
        int[][] c = new int[m+1][n+1];

        for(int i=1; i<=m; i++){
            c[i][0] = 0;
        }

        for(int j=0; j<=n; j++){
            c[0][j] = 0;
        }

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(X[i-1]==Y[j-1]){
                    c[i][j] = c[i-1][j-1]+1;
                    b[i][j] = Directions.UPLEFT;
                }else if(c[i-1][j]>=c[i][j-1]){
                    c[i][j] = c[i-1][j];
                    b[i][j] = Directions.UP;
                }else{
                    c[i][j] = c[i][j-1];
                    b[i][j] = Directions.LEFT;
                }
            }
        }

        TableBAndC tableBAndC = new TableBAndC();
        tableBAndC.c = c;
        tableBAndC.b = b;
        return tableBAndC;
    }

    /*
    Function from [Cormen, 2009]
    @param b is the table that helps reconstruct the longest common subsequence by tracing back the optimal solution
           X is one of the input sequences.
           i, j are last row and column of the table b respectively.
           finalLCS is the StringBuilder object to construct the LCS.
    @return the StringBuilder object which is the longest common subsequence.

    @description Reconstruct LCS using table b.
                 Based on PRINT-LCS pseudocode, [Cormen, 2009] p. 395.
     */
    StringBuilder reconstructLCS(Directions[][] b, char[] X, int i, int j, StringBuilder finalLCS){
        if(i==0 || j==0)
            return new StringBuilder();

        if(b[i][j]==Directions.UPLEFT){
            reconstructLCS(b, X, i-1, j-1, finalLCS);
            finalLCS.append(X[i-1]);
        }

        else if(b[i][j]==Directions.UP){
            reconstructLCS(b, X, i-1, j, finalLCS);
        }

        else{
            reconstructLCS(b, X, i, j-1, finalLCS);
        }
        return finalLCS;
    }
}

/**
 * Utility class to write the output into a file
 */
class OutputFileWriter {

    /*
    Utility function to write string into the file in a specified format
    @param BufferedWriter object
    @param value is the string to be written into the file
     */
    void writeToAFile(BufferedWriter writer, String value) throws IOException {
        writer.write(value);
    }
}

/**
 * Enum class to keep track of the optimal subproblems
 */
enum Directions{
    LEFT,
    UP,
    UPLEFT
}

/**
 * Class to create a return value for LCSLength method
 */
class TableBAndC{
    int[][] c;
    Directions[][] b;
}
