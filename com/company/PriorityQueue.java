package com.company;

import java.util.Arrays;

public class PriorityQueue {

    public static void main(String[] args) {
        int[] A = new int[] {4,1,3,2,16,9,10,14,8,7};
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.build_max_heap(A);
        System.out.println(Arrays.toString(A));
        System.out.println("max: "+priorityQueue.heap_extract_max(A, A.length-1));
        System.out.println(Arrays.toString(A));
        A = priorityQueue.max_heap_insert(A, A.length-1, 1);
        System.out.println(Arrays.toString(A));
    }

    void max_heapify(int[] A, int heap_size, int i)
    {
        int l = left(i);
        int r = right(i);
        int largest;
        if( l < heap_size && A[l] < A[i])
            largest = l;
        else
            largest = i;

        if(r < heap_size && A[r] < A[largest])
            largest = r;


        if (largest != i)
        {
            int exchange = A[i];
            A[i] = A[largest];
            A[largest] = exchange;

            max_heapify(A, heap_size, largest);
        }
    }

    int heap_maximum (int[] A)
    {
        return A[0];
    }

    int heap_extract_max (int[] A, int heap_size)
    {
        if (heap_size < 1)
        {
            System.out.println("Heap underflow");
        }
        int max = A[0];
        A[0] = A[heap_size];
        heap_size--;
        max_heapify (A, heap_size, 0);
        return max;
    }

    int[] heap_increase_key(int[] A, int i, int key)
    {
        if( key < A[i])
            System.out.println("New key is smaller than current key");

        A[i] = key;

        int exchange;
        while ( i > 0 && A[parent(i)] > A[i])
        {
            exchange = A[i];
            A[i] = A[parent(i)];
            A[parent(i)] = exchange;

            i = parent(i);
        }
        return A;
    }

    int[] max_heap_insert(int[] A, int heap_size, int key)
    {
        heap_size++;
        System.out.println("before: "+A.length);
        int[] newArr = Arrays.copyOf(A, A.length + 1);
        A = newArr;
        System.out.println("in: "+Arrays.toString(A));
        System.out.println("after: "+A.length);
        A[heap_size] = Integer.MIN_VALUE;
        System.out.println("after======: "+Arrays.toString(A));
        return heap_increase_key(A, heap_size, key);
    }

    void build_max_heap(int[] A)
    {
        for(int i = (A.length / 2); i >= 0; i--)
        {
            max_heapify(A,  A.length, i);
        }
    }

    void heapsort(int[] A)
    {
        build_max_heap(A);

        int heap_size = A.length;
        int exchange;

        for(int i = A.length-1; i >= 1; i--)
        {
            exchange = A[i];
            A[i] = A[0];
            A[0] = exchange;

            heap_size --;
            max_heapify(A, heap_size, 0);
        }
    }

    int parent(int i)
    {
        return i/2;
    }

    int left(int i)
    {
        return 2*i ;
    }

    int right(int i)
    {
        return 2*i + 1;
    }
}
