package heaps;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @project CS 617
 * @program Dijkstra's
 * @purpose Provide example implementation of Dijkstra's algorithm.
 *
 * @author Meghana Nagarahalli Paramesh
 *
 * Instructions to run the program:
 * Step 1: javac Dijkstra.java
 * Step 2: java Dijkstra
 */
public class Dijkstra {

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

    static class AdjListNode<T> {
        char vertex;
        int weight;

        AdjListNode(char v, int w)
        {
            vertex = v;
            weight = w;
        }
        char getVertex() { return vertex; }
        int getWeight() { return weight; }
    }
    static class Vertices {
        char vertex;
        ArrayList<AdjListNode> adjListNodes;

        Vertices(char vertex, ArrayList<AdjListNode> adjListNodes){
            this.vertex = vertex;
            this.adjListNodes = adjListNodes;
        }
    }

    public static class NodeDistance{

        public LinkedHashSet<Character> getPath() {
            return path;
        }

        public void setPath(LinkedHashSet<Character> path) {
            this.path = path;
        }

        LinkedHashSet<Character> path;
        public int getIndex() {
            return index;
        }

        @Override
        public String toString() {
            return "NodeDistance{" +
                    "path=" + path +
                    ", label=" + label +
                    ", index=" + index +
                    ", dist=" + dist +
                    ", pi=" + pi +
                    '}';
        }

        public void setIndex(int index) {
            this.index = index;
        }

        char label;
        int index;


        int dist;

        public int getPi() {
            return pi;
        }

        int pi;

        public void setLabel(char label) {
            this.label = label;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }

        public void setPi(int pi) {
            this.pi = pi;
        }
        public NodeDistance(char label, int dist, int index) {
            this.label = label;
            this.dist = dist;
            this.index = index;
        }

        public char getLabel() {
            return label;
        }

        public int getDist() {
            return dist;
        }
    }

    static class Graph{

        int size;
        Map<Character, Vertices> adj;
        NodeDistance[] V;

        public Graph(Map<Character, Vertices> adj, NodeDistance[] v) {
            this.adj = adj;
            this.V = v;
        }

        public Map<Character, Vertices> getAdj() {
            return adj;
        }

        public NodeDistance[] getV() {
            return V;
        }
    }
    public static void main(String[] args) {
        int V = 6;

        /**
         * Test case 1
         */
        Graph G1 = testcase1();
        char source = 'x';
        List<NodeDistance> result1 = dijkstra(G1, source);

        /**
         * Test case2
         */
        source = 'a';
        Graph G2 = testCase2And3(source);
        List<NodeDistance> result2 = dijkstra(G2, source);

        /**
         * Test case2
         */
        source = 'h';
        Graph G3 = testCase2And3(source);
        List<NodeDistance> result3 = dijkstra(G3, source);
        try {
            outputFileWriter.writeOutputIntoFile(writer, result1, 1);
            outputFileWriter.writeOutputIntoFile(writer, result2, 2);
            outputFileWriter.writeOutputIntoFile(writer, result3, 3);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<NodeDistance> dijkstra(Graph G, char s){
        initialize_single_source(G.V, s);

        PriorityQueue Q = new PriorityQueue();
        Q.build_min_heap(G.V, G.V.length-1);

        List<NodeDistance> result = new ArrayList<>();
        while (Q.getHeap_size()>0){
            NodeDistance u = Q.heap_extract_min(G.V);
            u.getPath().add(u.getLabel());
            result.add(u);

            /**
             * Parse adjacent vertices of node u
             */

            if(G.adj.get(u.getLabel())==null)
                continue;
            List<AdjListNode> adjListNodes = G.adj.get(u.getLabel()).adjListNodes;
            int idx = G.adj.get(u.getLabel()).vertex;
            for(AdjListNode v: adjListNodes){
                relax(v, u, G.V, Q);
            }
        }
        return result;
    }

    private static void relax(AdjListNode v, NodeDistance u, NodeDistance[] V, PriorityQueue Q){
        NodeDistance vNode=null;
        for(int i=0; i<V.length; i++){
            if (V[i].getLabel() == v.getVertex()) {
                vNode = V[i];
            }
        }

        if(vNode==null){
            return;
        }

        if(vNode.getPath()==null){
            LinkedHashSet<Character> path = new LinkedHashSet<>();
            path.add(u.getLabel());
            vNode.setPath(path);
        }
        if(vNode.getDist() > u.getDist()+v.getWeight()){
            vNode.setDist(u.getDist()+v.getWeight());
            vNode.setPi(u.getIndex());
            LinkedHashSet<Character> copyOfSet = new LinkedHashSet<>();

            copyOfSet.addAll(u.getPath());
            vNode.setPath(copyOfSet);
            vNode.getPath().add(u.getLabel());
            Q.heap_decrease_key(V, vNode, u.getDist()+v.getWeight(), Q.getHeap_size()-1);
        }
    }

    private static void initialize_single_source(NodeDistance[] V, char s){
        for(int i=0; i<V.length; i++){
            V[i].setDist(Integer.MAX_VALUE);
            V[i].setPi(-1);

            if(V[i].getLabel()==s)
                V[i].setDist(0);
        }
    }

    public static Graph testcase1(){
        int V=6;
        ArrayList<AdjListNode> adjListNodes1 = new ArrayList<>();
        adjListNodes1.add(new AdjListNode('a', 1));
        adjListNodes1.add(new AdjListNode('b', 5));
        Vertices vertices1 = new Vertices('x', adjListNodes1);


        ArrayList<AdjListNode> adjListNodes2 = new ArrayList<>();
        adjListNodes2.add(new AdjListNode('b', 6));
        adjListNodes2.add(new AdjListNode('d', 2));
        adjListNodes2.add(new AdjListNode('c', 3));
        Vertices vertices2 = new Vertices('a', adjListNodes2);

        ArrayList<AdjListNode> adjListNodes3 = new ArrayList<>();
        adjListNodes3.add(new AdjListNode('d', 2));
        Vertices vertices3 = new Vertices('b', adjListNodes3);

        ArrayList<AdjListNode> adjListNodes4 = new ArrayList<>();
        adjListNodes4.add(new AdjListNode('d', 3));
        adjListNodes4.add(new AdjListNode('z', 2));
        Vertices vertices4 = new Vertices('c', adjListNodes4);

        ArrayList<AdjListNode> adjListNodes5 = new ArrayList<>();
        adjListNodes5.add(new AdjListNode('z', 2));
        Vertices vertices5 = new Vertices('d', adjListNodes5);

        Map<Character, Vertices> adj = new HashMap<>();
        adj.put('x', vertices1);
        adj.put('a', vertices2);
        adj.put('b', vertices3);
        adj.put('c', vertices4);
        adj.put('d', vertices5);

        NodeDistance[] nodeDistance = new NodeDistance[V];
        nodeDistance[0] = new NodeDistance('x',0, 1);
        nodeDistance[1] = new NodeDistance('a', 0, 2);
        nodeDistance[2] = new NodeDistance('b',0, 3);
        nodeDistance[3] = new NodeDistance('c',0, 4);
        nodeDistance[4] = new NodeDistance('d',0, 5);
        nodeDistance[5] = new NodeDistance('z',0, 6);
        nodeDistance[0].setPath(new LinkedHashSet<>());
        nodeDistance[0].getPath().add('x');

        Graph G = new Graph(adj, nodeDistance);
        return G;
    }

    public static Graph testCase2And3(char source){
        int V=12;
        NodeDistance[] nodeDistanceTest2 = new NodeDistance[V];
        nodeDistanceTest2[0] = new NodeDistance('a',0, 1);
        nodeDistanceTest2[1] = new NodeDistance('b', 0, 2);
        nodeDistanceTest2[2] = new NodeDistance('c',0, 3);
        nodeDistanceTest2[3] = new NodeDistance('d',0, 4);
        nodeDistanceTest2[4] = new NodeDistance('e',0, 5);
        nodeDistanceTest2[5] = new NodeDistance('f',0, 6);

        nodeDistanceTest2[6] = new NodeDistance('g',0, 7);
        nodeDistanceTest2[7] = new NodeDistance('h', 0, 8);
        nodeDistanceTest2[8] = new NodeDistance('i',0, 9);
        nodeDistanceTest2[9] = new NodeDistance('j',0, 10);
        nodeDistanceTest2[10] = new NodeDistance('k',0, 11);
        nodeDistanceTest2[11] = new NodeDistance('l',0, 12);

        ArrayList<AdjListNode> adjListNodes1 = new ArrayList<>();
        adjListNodes1.add(new AdjListNode('f',1));
        Vertices vertices1 = new Vertices('a',adjListNodes1);

        ArrayList<AdjListNode> adjListNodes2 = new ArrayList<>();
        adjListNodes2.add(new AdjListNode('a',8));
        adjListNodes2.add(new AdjListNode('e',2));
        adjListNodes2.add(new AdjListNode('f',8));
        adjListNodes2.add(new AdjListNode('g',9));
        Vertices vertices2 = new Vertices('b', adjListNodes2);

        ArrayList<AdjListNode> adjListNodes3 = new ArrayList<>();
        adjListNodes3.add(new AdjListNode('d',9));
        adjListNodes3.add(new AdjListNode('f',10));
        adjListNodes3.add(new AdjListNode('g',8));
        adjListNodes3.add(new AdjListNode('b',2));
        Vertices vertices3 = new Vertices('c', adjListNodes3);

        ArrayList<AdjListNode> adjListNodes4 = new ArrayList<>();
        adjListNodes4.add(new AdjListNode('a',10));
        adjListNodes4.add(new AdjListNode('i',2));
        adjListNodes4.add(new AdjListNode('j',8));
        Vertices vertices4 = new Vertices('e', adjListNodes4);

        ArrayList<AdjListNode> adjListNodes5 = new ArrayList<>();
        adjListNodes5.add(new AdjListNode('i',11));
        adjListNodes5.add(new AdjListNode('k',1));
        Vertices vertices5 = new Vertices('f', adjListNodes5);

        ArrayList<AdjListNode> adjListNodes6 = new ArrayList<>();
        adjListNodes6.add(new AdjListNode('l',1));
        adjListNodes6.add(new AdjListNode('d',9));
        Vertices vertices6 = new Vertices('g', adjListNodes6);

        ArrayList<AdjListNode> adjListNodes7 = new ArrayList<>();
        adjListNodes7.add(new AdjListNode('c',1));
        adjListNodes7.add(new AdjListNode('k',9));
        adjListNodes7.add(new AdjListNode('d',10));
        Vertices vertices7 = new Vertices('h', adjListNodes7);

        ArrayList<AdjListNode> adjListNodes8 = new ArrayList<>();
        adjListNodes8.add(new AdjListNode('j',10));
        Vertices vertices8 = new Vertices('i', adjListNodes8);

        ArrayList<AdjListNode> adjListNodes9 = new ArrayList<>();
        adjListNodes9.add(new AdjListNode('f',10));
        adjListNodes9.add(new AdjListNode('g',2));
        Vertices vertices9 = new Vertices('j', adjListNodes9);

        ArrayList<AdjListNode> adjListNodes10 = new ArrayList<>();
        adjListNodes10.add(new AdjListNode('j',1));
        adjListNodes10.add(new AdjListNode('l',8));
        Vertices vertices10 = new Vertices('k', adjListNodes10);

        ArrayList<AdjListNode> adjListNodes11 = new ArrayList<>();
        adjListNodes11.add(new AdjListNode('h',2));
        Vertices vertices11 = new Vertices('l', adjListNodes11);

        Map<Character, Vertices> adj = new HashMap<>();
        adj.put('a',vertices1);
        adj.put('b',vertices2);
        adj.put('c',vertices3);
        adj.put('e', vertices4);
        adj.put('f',vertices5);
        adj.put('g',vertices6);
        adj.put('h',vertices7);
        adj.put('i',vertices8);
        adj.put('j', vertices9);
        adj.put('k', vertices10);
        adj.put('l',vertices11);

        if(source=='h'){
            nodeDistanceTest2[7].setPath(new LinkedHashSet<>());
            nodeDistanceTest2[7].getPath().add('h');
        }

        if(source=='a'){
            nodeDistanceTest2[0].setPath(new LinkedHashSet<>());
            nodeDistanceTest2[0].getPath().add('a');
        }

        Graph graph = new Graph(adj, nodeDistanceTest2);
        return graph;
    }
}

class PriorityQueue
{
    private int heap_size;

    public int getHeap_size(){
        return this.heap_size+1;
    }
    public void min_heapify(Dijkstra.NodeDistance[] A, int i)
    {
        int l = left(i);
        int r = right(i);
        int smallest;
        if( l <= heap_size && A[l].getDist() < A[i].getDist())
            smallest = l;
        else
            smallest = i;

        if(r <= heap_size && A[r].getDist() < A[smallest].getDist())
            smallest = r;


        if (smallest != i)
        {
            Dijkstra.NodeDistance exchange = A[i];
            A[i] = A[smallest];
            A[smallest] = exchange;

            min_heapify(A, smallest);
        }
    }

    int heap_minimum (int[] A)
    {
        return A[0];
    }

    public Dijkstra.NodeDistance heap_extract_min (Dijkstra.NodeDistance[] A)
    {
        if (heap_size < 0)
        {
            System.out.println("Heap underflow");
        }
        Dijkstra.NodeDistance min = A[0];
        A[0] = A[heap_size];
        heap_size--;
        min_heapify(A, 0);
        return min;
    }

    public void heap_decrease_key(Dijkstra.NodeDistance[] A, Dijkstra.NodeDistance v, int newWeight, int heap_size){
        v.setDist(newWeight);
        build_min_heap(A, heap_size);
    }

    public void build_min_heap(Dijkstra.NodeDistance[] A, int heap_size)
    {
        this.heap_size=heap_size;
        for(int i = (A.length / 2); i >= 0; i--)
        {
            min_heapify(A, i);
        }
    }

    void heapsort(Dijkstra.NodeDistance[] A)
    {
        int heap_size = A.length;
        Dijkstra.NodeDistance exchange;
        build_min_heap(A, heap_size-1);
        for(int i = A.length-1; i >= 1; i--)
        {
            exchange = A[i];
            A[i] = A[0];
            A[0] = exchange;

            heap_size --;
            min_heapify(A, 0);
        }
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
}

/**
 * Utility class to write the output into a file
 */
class OutputFileWriter{

    /*
    Utility function to write array into the file in a specified format
    @param BufferedWriter object
    @param array
    @param value is the output string to be printed before the array
     */
    void writeOutputIntoFile(BufferedWriter writer, List<Dijkstra.NodeDistance> nodeDistanceList, int graphNumber) throws IOException {
        Collections.sort(nodeDistanceList, (Dijkstra.NodeDistance node1, Dijkstra.NodeDistance node2) ->{
            return node1.getIndex()-node2.getIndex();
        });
        writer.write("graph= "+graphNumber+"\n");
        writer.write("result p="+getPiString(nodeDistanceList)+"\n");
        writer.write("result d="+getDistString(nodeDistanceList)+"\n");

        for(int i=0; i<nodeDistanceList.size(); i++){
            String[] pathString = getFirstLastArrayString(nodeDistanceList.get(i).getPath());
            writer.write("from= "+pathString[0]+" to= "+pathString[1]+" path= "+pathString[2]+" weight= "+nodeDistanceList.get(i).getDist()+"\n");
        }
        writer.write("\n");
    }

    String[] getFirstLastArrayString(LinkedHashSet<Character> path){
        Character[] pathArray = path.toArray(new Character[path.size()]);
        String[] pathString = new String[3];
        pathString[0] = pathArray[0].toString();
        pathString[1] = pathArray[path.size()-1].toString();

        StringBuilder stringBuilder = new StringBuilder();
        for(char eachPath: path){
            stringBuilder.append(eachPath+" ");
        }
        pathString[2] = stringBuilder.toString();

        return pathString;
    }
    String getPiString(List<Dijkstra.NodeDistance> nodeDistanceList){
        StringBuilder piString = new StringBuilder();
        for(int i=0; i<nodeDistanceList.size(); i++){
            if(nodeDistanceList.get(i).getPi() == -1)
                piString.append(" NA");
            else
                piString.append(" " + nodeDistanceList.get(i).getPi());
        }
        return piString.toString();
    }

    String getDistString(List<Dijkstra.NodeDistance> nodeDistanceList){
        StringBuilder distString = new StringBuilder();
        for(int i=0; i<nodeDistanceList.size(); i++){
            distString.append(" "+nodeDistanceList.get(i).getDist());
        }
        return distString.toString();
    }
}


