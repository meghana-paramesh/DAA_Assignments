package com.company;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HeapMin {
    static BufferedWriter writer;

    /*
    Static block to initialize the BufferedWriter so that it can be used throughout the class
     */
    static {
        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /*
    Utility class for writing into output file
     */
    static OutputFileWriter outputFileWriter = new OutputFileWriter();

    Set<int[]> set = new HashSet<>();
    public static void main(String[] args) throws IOException {

        /*
        Read the input file from the same resource path as the source code file
         */
//        URL path = Heap.class.getResource("input.txt");
//        File file = new File(path.getFile());
//
//
//        /*
//        Initializing a bufferedReader to read from the file.
//        Catch proper exceptions if error occurs while reading from file or writing into the file
//         */
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
//            String line;
//            int testcase = 1;
//
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//                outputFileWriter.writeToAFile(writer, "test case    = " + testcase++ + "\n");
//
//                String[] tokens = line.split(" ");
//                int[] array = new int[tokens.length];
//                for (int i = 0; i < array.length; i++) {
//                    array[i] = Integer.parseInt(tokens[i]);
//                }
//
//                Heap heap = new Heap();
//                outputFileWriter.writeArrayIntoFile(writer, array, "before build = ");
//                outputFileWriter.writeArrayIntoFile(writer, heap.heapSort(array), "after sort   = ");
//                outputFileWriter.writeToAFile(writer,"\n");
//            }
//            /*
//            Close the writer object to avoid buffer overflow
//             */
//            writer.close();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }

        HeapMin heap = new HeapMin();
        int[] arr = new int[]{5, 13, 2, 25, 7, 17, 20, 8, 4};
        heap.heapSort(arr);
    }

    /*
    Function from [Cormen, 2009]
    @param ith node
    @returns position of i's parent node
     */
    int parent(int i) {
        return (i - 1) / 2;
    }

    /*
    Function from [Cormen, 2009]
    @param ith node
    @returns position of i's left child
     */
    int left(int i) {
        return 2 * i + 1;
    }

    /*
    Function from [Cormen, 2009]
    @param i ith node
    @return position of i's right child
     */
    int right(int i) {
        return 2 * i + 2;
    }

    /*
    Function from [Cormen, 2009]
    @param array input array
    @param i ith node from which we perform maxHeapify
    @param heapSize
     */
    void maxHeapify(int[] array, int i, int heapSize) {

        int l = left(i);
        int r = right(i);
        int smallest = 0;

        if (l <= heapSize && array[l] > array[i])
            smallest = l;
        else smallest = i;

        if (r <= heapSize && array[r] > array[smallest])
            smallest = r;

        if (smallest != i) {
            swap(array, i, smallest);
            //for build heap working
            System.out.println("max heap: "+Arrays.toString(array));
            maxHeapify(array, smallest, heapSize);

        }

    }

    /*
    Function from [Cormen, 2009]
    @param input array
    @param heapsize
     */
    void buildMaxHeap(int[] array, int heapsize) {
        for (int i = (array.length) / 2; i >= 0; i--) {
//            System.out.println("max heap: "+Arrays.toString(array));
            maxHeapify(array, i, heapsize);

        }
    }

    /*
    Function from [Cormen, 2009]
    @param input array
    @return sorted array
     */
    int[] heapSort(int[] array) throws IOException {
        int heapsize = array.length - 1;
        System.out.println();
        buildMaxHeap(array, heapsize);
//        System.out.println("after build  = "+Arrays.toString(array));
//        outputFileWriter.writeToAFile(writer, "verified     = "+(verifyHeap(array, heapsize)?"TRUE":"FALSE")+"\n");
        for (int i = (array.length - 1); i > 0; i--) {
            swap(array, 0, i);
            System.out.println(Arrays.toString(array));
            heapsize--;
            maxHeapify(array, 0, heapsize);
        }
        return array;
    }

    /*
    Utility function to elements in the array
    @param input array
    @param ith index in the array
    @param jth index in the array
     */
    void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /*
    Utility function to verify that the current contents of the heap array satisfy the heap property
    @param input array
    @param length of the array
     */
    boolean verifyHeap(int array[], int n) {
        for(int i=0; i<=(n-2)/2; i++){
            if(2*i+1<n && array[2*i+1]>array[i])
                return false;

            if(2*i+2<n && array[2*i+2]>array[i])
                return false;
        }
        return true;
    }
}


