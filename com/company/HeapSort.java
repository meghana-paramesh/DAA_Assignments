package com.company;

import java.io.*;
import java.net.URL;

/**
 * @project CS 617
 * @program Heaps and heapsort
 * @author Meghana Nagarahalli Paramesh
 */
public class HeapSort {
    static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {

        /*Reading from a file in the same resource path as the
        *
         */
        URL path = HeapSort.class.getResource("input.txt");
        File file = new File(path.getFile());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            int testcase=1;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                writer.write("test case    = "+testcase+++"\n");

                String[] tokens = line.split(" ");
                int[] arr = new int[tokens.length];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = Integer.parseInt(tokens[i]);
                }

//                writer.write("before build = "+Arrays.toString(arr)+"\n");
                HeapNew heap = new HeapNew(writer);
                heap.writeArrayIntoFile(writer, arr, "before build = ");
//                writer.write("after sort   = "+(heap.heapSort(arr))+"\n");
                heap.writeArrayIntoFile(writer, heap.heapSort(arr), "after sort   = ");
                writer.write("\n");
            }
            writer.close();

        } catch (IOException exception) {
            System.out.println("Error occured: " + exception);
        }
    }
}


class HeapNew {
    BufferedWriter writer;
    HeapNew(BufferedWriter bufferedWriter){
        this.writer = bufferedWriter;
    }
    int parent(int i) {
        return (i - 1) / 2;
    }

    int left(int i) {
        return 2 * i + 1;
    }

    int right(int i) {
        return 2 * i + 2;
    }

    void maxHeapify(int[] arr, int i, int heapSize) {
        int l = left(i);
        int r = right(i);
        int largest = 0;

        if (l <= heapSize && arr[l] > arr[i])
            largest = l;
        else largest = i;

        if (r <= heapSize && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            swap(arr, i, largest);

            maxHeapify(arr, largest, heapSize);
        }

    }

    void buildMaxHeap(int[] arr, int heapsize) {
        for (int i = (arr.length) / 2; i >= 0; i--) {
            maxHeapify(arr, i, heapsize);
        }
    }

    int[] heapSort(int[] arr) throws IOException {
        int heapsize = arr.length - 1;
        buildMaxHeap(arr, heapsize);
//        writer.write("after build  = " + Arrays.toString(arr)+"\n");
        writeArrayIntoFile(writer, arr, "after build  = ");
        writer.write("verified     = "+(verifyHeap(arr, heapsize)?"TRUE":"FALSE")+"\n");
        for (int i = (arr.length - 1); i > 0; i--) {
            swap(arr, 0, i);
            heapsize--;
            maxHeapify(arr, 0, heapsize);
        }
        return arr;
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    boolean verifyHeap(int arr[], int n) {
        for(int i=0; i<=(n-2)/2; i++){
            if(2*i+1<n && arr[2*i+1]>arr[i])
                return false;

            if(2*i+2<n && arr[2*i+2]>arr[i])
                return false;
        }
        return true;
    }

    void writeArrayIntoFile(BufferedWriter writer, int[] arr, String type) throws IOException {
        String outputArray = "";
        for(int i=0; i<arr.length; i++){
            outputArray+=arr[i]+" ";
        }
        writer.write(type+outputArray+"\n");
    }
}