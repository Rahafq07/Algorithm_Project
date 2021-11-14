

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import javafx.util.Pair;

/**
 * <h1>Comparison between Algorithms</h1>
 * 
 * the program will Compare between Kruskal Algorithm ,
 * Prim Algorithm using Priority Queue and Prim Algorithm using Min-Heap
 * We shall use these algorithms for finding the minimum spanning tree, 
 * compare between them by calculating the running time of each algorithm.
 * 
 * @author Rahaf , sarah , Somayah 
 * @version 8.2
 * @since 11-11-2021
 */
public class NewMain {

    static Graph graph;
    
    /**
     * This is the main method which print the start sentence and takes the case defined by the user 
     * then directs the selection using switch to specify the number of vertexs and edges, 
     * the sends it to make a graph and then prints each algorithm with a minimum spanning tree and its execution time.
     * 
     * @param args is the parameter to the main Method.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = 0, m = 0;

        System.out.println("======================================================================================================================\n"
                         + "   A Comparison between Kruskal Algorithm ,Prim Algorithm using Priority Queue and Prim Algorithm using Min-Heap\n"
                         + "======================================================================================================================");
        System.out.println("\nEnter your case to make a graph (n : number of vertix , m : number of eadges ):\n");
        System.out.println("1. n = 1000, m = 10000 \n"
                + "2. n = 1000, m = 15000 \n"
                + "3. n = 1000, m = 25000 \n"
                + "4. n = 5000, m = 15000 \n"
                + "5. n = 5000, m = 25000 \n"
                + "6. n = 10000, m = 15000 \n"
                + "7. n = 10000, m = 25000 \n"
                + "8. n = 20000, m = 200000 \n"
                + "9. n = 20000, m = 300000 \n"
                + "10. n = 50000, m = 10000000 ");
        System.out.print("\nthe case is : ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                n = 1000;
                m = 10000;
                break;
            case 2:
                n = 1000;
                m = 15000;
                break;
            case 3:
                n = 1000;
                m = 25000;
                break;
            case 4:
                n = 5000;
                m = 15000;
                break;
            case 5:
                n = 5000;
                m = 25000;
                break;
            case 6:
                n = 10000;
                m = 15000;
                break;
            case 7:
                n = 10000;
                m = 25000;
                break;
            case 8:
                n = 20000;
                m = 200000;
                break;
            case 9:
                n = 20000;
                m = 300000;
                break;
            case 10:
                n = 50000;
                m = 10000000;
                break;
            default:
                System.out.println("Invalid input");
        }
        
        graph = new Graph(n,m);
        make_graph(graph);

        
        //Kruskal Algorithm
        
        System.out.println("\n============ Kruskal Algorithm ============\n");
        System.out.print("Minimum Spanning Tree using Kruskal Algorithm is: ");
        double startTime = System.currentTimeMillis(); //Start time
        Kruskal(graph); // apply Kruskal Algorithm
        double endTime = System.currentTimeMillis(); //End time
        double totalTime = endTime - startTime; //Total time
        System.out.println("\nRunning time of Kruskal algorithm is: " + totalTime + " milli seconds\n");

        //Prim Algorithm using Priority Queue
        
        System.out.println("============Prim Algorithm using Priority Queue============\n");
        System.out.print("Minimum Spanning Tree using Prim’s Algorithm is: ");
        startTime = System.currentTimeMillis(); //Start time
        primPriority_Q(graph); // apply prim algorithm using priority queue
        endTime = System.currentTimeMillis(); //End time
        totalTime = endTime - startTime;
        System.out.println("\nRunning time of Prim’s algorithm using Priority Queue is: " + totalTime + " milli seconds\n");

        //Prim Algorithm using Min-Heap
        
        System.out.println("============Prim Algorithm using Min-Heap============\n");
        System.out.print("Minimum Spanning Tree using Prim’s Algorithm is: ");
        startTime = System.currentTimeMillis(); //Start time
        primMinHeap(graph); // apply prim algorithm using min heap
        endTime = System.currentTimeMillis(); //End time
        totalTime = endTime - startTime; //Total time
        System.out.println("\nRunning time of Prim’s algorithm using Min-Heap is: " + totalTime + " milli seconds\n");

    }

    /**
     * This is the make_graph method which Generates random numbers of vertices (Source, Destination) and weight,
     * then sends them to the GRAPH class in order to create an edge.
     * 
     * @param graph the graph that contain vertices and edges.
     */
    public static void make_graph(Graph graph) {

        // instance of Random class
        Random random = new Random();
        
        // ensure that all vertices are connected
        for (int i = 0; i < graph.vertices - 1; i++) {
            int weight = random.nextInt(20) + 1;
            graph.addEdge(i, i + 1, weight);

        }

        // generate random graph with the remaining edges
        int remaning = graph.edges - (graph.vertices - 1);

        for (int i = 0; i < remaning; i++) {
            int source = random.nextInt(graph.vertices);
            int destination = random.nextInt(graph.vertices);
            
            // to avoid self loops and duplicate edges
            if (destination == source || graph.isConnected(source, destination, graph.adjacencylist)) { 
                i--;
                continue;
            }
            
            // generate random weights in range 0 to 20
            int weight = random.nextInt(20) + 1;
            // add edge to the graph
            graph.addEdge(source, destination, weight);
        }

    }
    
    /**
     * This is the Kruskal method which add all the edges to the priority Queue 
     * and arrange them according to weight, then add them to the linked list As long as it's not make a cycle
     * Also its calculate the Minimum Spanning Tree.
     * 
     * @param graph the graph that contain vertices and edges. 
     */
    public static void Kruskal(Graph graph) {

        
        LinkedList<Edge>[] allEdges = graph.adjacencylist.clone(); // modified data type from ArrayList to LinkedList
        PriorityQueue<Edge> pq = new PriorityQueue<>(graph.edges, Comparator.comparingInt(o -> o.weight));

        //add all the edges to priority queue,sort the edges on weights
        for (int i = 0; i < allEdges.length; i++) {
            for (int j = 0; j < allEdges[i].size(); j++) {
                pq.add(allEdges[i].get(j));
            }
        }

        //create a parent []
        int[] parent = new int[graph.vertices];

        //makeset
        makeSet(parent);

        LinkedList<Edge> mst = new LinkedList<>();

        //process vertices - 1 edges
        int index = 0;
        while (index < graph.vertices - 1 && !pq.isEmpty()) {
            Edge edge = pq.remove();
            //check if adding this edge creates a cycle
            int x_set = find(parent, edge.source);
            int y_set = find(parent, edge.destination);

            if (x_set == y_set) {
                //ignore, will create cycle
            } else {
                //add it to our final result
                mst.add(edge);
                index++;
                union(parent, x_set, y_set);
            }
            
        }

        int cost = 0;
        for (int i = 0; i < mst.size(); i++) {
            Edge edge = mst.get(i);
            cost += edge.weight;
        }
        System.out.println(cost);

    }

    /**
     * This is the primPriority_Q method which Initialize all the keys to infinity and initialize setOFresult for all the vertices
     * And override the comparator to do the sorting based keys
     * And create the pair for the first index then add it to priority queue
     * And extract the min and iterate through all the adjacent vertices and update the keys
     * Also its calculate the Minimum Spanning Tree.
     * 
     * @param graph the graph that contain vertices and edges.
     */
    public static void primPriority_Q(Graph graph) {

        
        boolean[] mst = new boolean[graph.vertices];
        SetOfResult[] setOFresult = new SetOfResult[graph.vertices];
        
        //keys used to store the key to know whether priority queue update is required
        int[] key = new int[graph.vertices];  

        //Initialize all the keys to infinity and initialize setOFresult for all the vertices
        for (int i = 0; i < graph.vertices; i++) {
            key[i] = Integer.MAX_VALUE;
            setOFresult[i] = new SetOfResult();
        }

        //Initialize priority queue
        //override the comparator to do the sorting based keys
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(graph.vertices, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                //sort using key values
                int key1 = p1.getKey();
                int key2 = p2.getKey();
                return key1 - key2;
            }
        });

        //create the pair for the first index, 0 key 0 index
        key[0] = 0;
        Pair<Integer, Integer> p0 = new Pair<>(key[0], 0);
        //add it to pq
        pq.offer(p0);
        setOFresult[0] = new SetOfResult();
        setOFresult[0].parent = -1;

        //while priority queue is not empty
        while (!pq.isEmpty()) {
            //extract the min
            Pair<Integer, Integer> extractedPair = pq.poll();

            //extracted vertex
            int extractedVertex = extractedPair.getValue();
            mst[extractedVertex] = true;

            //iterate through all the adjacent vertices and update the keys
            LinkedList<Edge> list = graph.adjacencylist[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is not present in mst
                if (mst[edge.destination] == false) {
                    int destination = edge.destination;
                    int newKey = edge.weight;
                    //check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        //add it to the priority queue
                        Pair<Integer, Integer> p = new Pair<>(newKey, destination);
                        pq.offer(p);
                        //update the setOFresult for destination vertex
                        setOFresult[destination].parent = extractedVertex;
                        setOFresult[destination].weight = newKey;
                        //update the key[]
                        key[destination] = newKey;
                    }
                }
            }
        }
        
        int cost = 0;
        for (int i = 1; i < graph.vertices; i++) {
            cost += setOFresult[i].weight;
        }
        System.out.println(cost);
        
    }

    /**
     * This is the primMinHeap method which add all the vertices to the Min Heap 
     * and extract the min, then iterate through all the adjacent vertices
     * Also its calculate the Minimum Spanning Tree.
     * 
     * @param graph the graph that contain vertices and edges.
     */
    public static void primMinHeap(Graph graph) {

        
        boolean[] inHeap = new boolean[graph.vertices];
        SetOfResult[] setOFresult = new SetOfResult[graph.vertices];
        
        //keys[] used to store the key to know whether min hea update is required
        int[] key = new int[graph.vertices];
        //create heapNode for all the vertices
        HeapNode[] heapNodes = new HeapNode[graph.vertices];
        
        for (int i = 0; i < graph.vertices; i++) {
            heapNodes[i] = new HeapNode();
            heapNodes[i].vertex = i;
            heapNodes[i].key = Integer.MAX_VALUE;
            setOFresult[i] = new SetOfResult();
            setOFresult[i].parent = -1;
            inHeap[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        //decrease the key for the first index
        heapNodes[0].key = 0;

        //add all the vertices to the MinHeap
        MinHeap minHeap = new MinHeap(graph.vertices);
        //add all the vertices to priority queue
        for (int i = 0; i < graph.vertices; i++) {
            minHeap.insert(heapNodes[i]);
        }

        //while minHeap is not empty
        while (!minHeap.isEmpty()) {
            //extract the min
            HeapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extractedNode.vertex;
            inHeap[extractedVertex] = false;

            //iterate through all the adjacent vertices
            LinkedList<Edge> list = graph.adjacencylist[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is present in heap
                if (inHeap[edge.destination]) {
                    int destination = edge.destination;
                    int newKey = edge.weight;
                    //check if updated key < existing key, if yes, update if
                    if (key[destination] > newKey) {
                        decKey(minHeap, newKey, destination);
                        //update the parent node for destination
                        setOFresult[destination].parent = extractedVertex;
                        setOFresult[destination].weight = newKey;
                        key[destination] = newKey;
                    }
                }
            }
        }
        
        int cost = 0;
        for (int i = 1; i < graph.vertices; i++) {
            cost += setOFresult[i].weight;
        }
        System.out.println(cost);
    }

    /**
     * This is the makeSet method which Make a set that create a new element with a parent pointer to itself.
     * 
     * @param parent is an array of integer that contain a set of element with a parent pointer to itself.
     */
    public static void makeSet(int[] parent) {
        //Make set creating a new element with a parent pointer to itself.
        for (int i = 0; i < graph.vertices; i++) {
            parent[i] = i;
        }
    }

    /**
     * This is the find method which return the vertex if found using chain of parent pointers
     * through the tree until an element is reached whose parent is itself.
     * 
     * @param parent is an array of integer that contain a set of element with a parent pointer to itself.
     * @param vertex  the vertex that we want to find.
     * @return vertex the vertex that will return if found.
     */
    public static int find(int[] parent, int vertex) {
        //chain of parent pointers from x upwards through the tree
        // until an element is reached whose parent is itself
        if (parent[vertex] != vertex) {
            return find(parent, parent[vertex]);
        };
        return vertex;
    }

    /**
     * This is the union method which find x and y set parent then make x as parent of y.
     * 
     * @param parent is an array of integer that contain a set of element with a parent pointer to itself.
     * @param x vertex x.
     * @param y vertex y.
     */
    public static void union(int[] parent, int x, int y) {
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        //make x as parent of y
        parent[y_set_parent] = x_set_parent;
    }

    /**
     * This is the decreaseKey method which get the index of key that need to decrease,
     * then get the node and update its value.
     * 
     * @param minHeap min heap that contain all vertex in graph.
     * @param newKey the weight of the edge.
     * @param vertex The vertex whose index needs to be decrease.
     */
    public static void decKey(MinHeap minHeap, int newKey, int vertex) {

        //get the index which key's needs a decrease;
        int index = minHeap.indexes[vertex];

        //get the node and update its value
        HeapNode node = minHeap.minHeap[index];
        node.key = newKey;
        minHeap.bubbleUp(index);
    }

}
