package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Prims {

    public static void main(String[] args) {
        LinkedList<VertexPrims> list = new LinkedList<>();
        VertexPrims vA = new VertexPrims('a');
        VertexPrims vB = new VertexPrims('b');
        VertexPrims vC = new VertexPrims('c');
        VertexPrims vD = new VertexPrims('d');
        VertexPrims vE = new VertexPrims('e');

        list.addAll(Arrays.asList(vA, vB, vC, vD, vE));

    }

    public void prims(VertexPrims r, LinkedList<VertexPrims> G) {
        LinkedList<VertexPrims> res = new LinkedList<>();
        PriorityQueue<VertexPrims> pqueue = new PriorityQueue<>();

        for(VertexPrims v: G){
            v.setKey(Integer.MAX_VALUE);
            v.setPi('0');
            pqueue.add(v);
        }
        r.setKey(0);

        while (!pqueue.isEmpty()){
            VertexPrims u = pqueue.poll();
            res.add(u);
            if(u.getAdjVertices()==null)
                continue;
            LinkedList<VertexPrims> adj = u.getAdjVertices();

            for(VertexPrims vertexPrims: adj){
                if(vertexPrims.getKey()>vertexPrims.getWeight()){
                    vertexPrims.setKey(vertexPrims.getWeight());
                    vertexPrims.setPi(u.getVer());
                }
            }
        }
    }
}

class EdgePrim{

}

class VertexPrims implements Comparable<VertexPrims>{
    LinkedList<VertexPrims> adjVertices;
    int key;
    char pi;
    char ver;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    int weight;

    public LinkedList<VertexPrims> getAdjVertices() {
        return adjVertices;
    }

    public void setAdjVertices(LinkedList<VertexPrims> adjVertices) {
        this.adjVertices = adjVertices;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public char getPi() {
        return pi;
    }

    public void setPi(char pi) {
        this.pi = pi;
    }

    public char getVer() {
        return ver;
    }

    public void setVer(char ver) {
        this.ver = ver;
    }

    VertexPrims(char ver){
        this.ver = ver;
    }

    @Override
    public int compareTo(VertexPrims t1) {
        Integer it = this.getKey();
        Integer it1 = t1.getKey();
        return it.compareTo(it1);
    }
}
