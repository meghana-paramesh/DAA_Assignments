package com.company;

import java.util.*;

public class BfsWithLevels {

    static class Vertex{
        LinkedList<Vertex> adjVertices;
        String color="white";
        int d;
        char pi;
        char ver;

        Vertex(char ver){
            this.ver = ver;
        }
    }

    LinkedList<Vertex> BFS(Vertex s) {
        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<Vertex> res = new LinkedList<>();
        queue.add(s);
        System.out.println("enqueue "+s.ver+": "+queueString(queue));

        while (queue.size() != 0) {
            Vertex curNode = queue.poll();
            res.add(curNode);
            System.out.println("dequeue "+curNode.ver+": "+queueString(queue));

            if(curNode.adjVertices==null)
                continue;

            LinkedList<Vertex> neighnours = curNode.adjVertices;
            for (Vertex neighbour: neighnours) {
                if (neighbour.color.equals("white")) {
                    neighbour.color = "grey";
                    neighbour.d = curNode.d+1;
                    neighbour.pi = curNode.ver;
                    queue.add(neighbour);
                    System.out.println("enqueue "+neighbour.ver+": "+queueString(queue));
                }
            }
            curNode.color="black";
        }
        return res;
    }

    Stack<Vertex> stack = new Stack<>();
    LinkedList<Vertex> result = new LinkedList<>();

    void DFS(LinkedList<Vertex> vertices){
        for(int i=0; i<vertices.size(); i++){
            if(vertices.get(i).color.equals("white"))
                DFS(vertices, vertices.get(i));
        }
    }

    void DFS(LinkedList<Vertex> vertices, Vertex s){
        stack.push(s);
        result.add(s);
        System.out.println("push "+s.ver+": "+queueString(stack));
        s.color="grey";
        LinkedList<Vertex> neighnours = s.adjVertices;

        for (Vertex neighbour: neighnours) {
            if (neighbour.color.equals("white")) {
                    neighbour.color = "grey";
                    neighbour.pi = s.ver;
                    DFS(vertices, neighbour);
                }
            }
        s.color="black";
        Vertex poped = stack.pop();
        System.out.println("pop "+poped.ver+": "+queueString(stack));
    }

    public String queueString(Stack<Vertex> stack){
        StringBuilder sb = new StringBuilder();
        for(Vertex v: stack){
            sb.append(v.ver+" ");
        }
        return sb.toString();
    }

    public String queueString(LinkedList<Vertex> queue){
        StringBuilder sb = new StringBuilder();
        for(Vertex v: queue){
            sb.append(v.ver+" ");
        }
        return sb.toString();
    }

    public static void main(String args[])
    {
        BfsWithLevels g = new BfsWithLevels();

        LinkedList<Vertex> vertices = new LinkedList<>();

        Vertex u = new Vertex('u');
        Vertex v = new Vertex('v');
        Vertex w = new Vertex('w');
        Vertex x = new Vertex('x');
        Vertex y = new Vertex('y');
        Vertex z = new Vertex('z');

        vertices.add(u);
        vertices.add(v);
        vertices.add(w);
        vertices.add(x);
        vertices.add(y);
        vertices.add(z);


        LinkedList<Vertex> adjU = new LinkedList<>();
        adjU.add(v);
        adjU.add(x);
        u.adjVertices = adjU;

        LinkedList<Vertex> adjV = new LinkedList<>();
        adjV.add(y);
        v.adjVertices = adjV;

        LinkedList<Vertex> adjW = new LinkedList<>();
        adjW.add(y);
        adjW.add(z);
        w.adjVertices = adjW;

        LinkedList<Vertex> adjX = new LinkedList<>();
        adjX.add(v);
        x.adjVertices = adjX;

        LinkedList<Vertex> adjY = new LinkedList<>();
        adjY.add(x);
        y.adjVertices = adjY;

        LinkedList<Vertex> adjZ = new LinkedList<>();
        adjZ.add(z);
        z.adjVertices = adjZ;

        System.out.println("Breadth First Traversal: ");
//        System.out.println("result: "+g.BFS(u));

        g.DFS(vertices);

        for(Vertex vertex: g.result){
            System.out.print(vertex.ver+" ");
        }
//        System.out.println();
//        for(Vertex vertex: res){
//            System.out.println("value: "+vertex.ver+" d:"+vertex.d+" pi:"+vertex.pi);
//        }
    }
}