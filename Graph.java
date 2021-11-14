
import java.util.LinkedList;

/***
 * <h1>Graph Class</h1>
 * This is the Graph class which has vertices,edges and LinkedList from type Edge as instance variable.
 * 
 * @author Rahaf , sarah , Somayah 
 * @version 8.2
 * @since 11-11-2021
 */
public class Graph {

    int vertices; // number of node in graph 
    int edges; // number of edges in graph
    LinkedList<Edge>[] adjacencylist; // LinkedList from Edge 

    /***
     * This is a  graph constructor that indicates parameters and initialize adjacency lists for all the vertices.
     * 
     * @param vertices number of vertices.
     * @param edges number of edges.
     */
    public Graph(int vertices, int edges) {
        
        this.vertices = vertices;
        this.edges = edges;
        adjacencylist = new LinkedList[vertices];
        
        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertices; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    /***
     * This is the addEdge method which Create an object of type Edge with new edge from source to destination  
     * then add First edge in adjacency list and creat new Edge from destination to the source 
     * then add it in adjacency list for undirected graph.
     * 
     * @param source source vertex.
     * @param destination destination vertex.
     * @param weight weight of edge between source and destination.
     */
    public void addEdge(int source, int destination, int weight) {
        
        //Create an object of type Edge with new edge from source to destination
        Edge edge = new Edge(source, destination, weight);
        adjacencylist[source].addFirst(edge);// add First edge in adjacency list

        //new Edge from destination to the source
        edge = new Edge(destination, source, weight);
        adjacencylist[destination].addFirst(edge); //for undirected graph

    }

    /***
     * This is the isConnected method which checks if the edge is already existed or not.
     * 
     * @param source source vertex.
     * @param destination destination vertex.
     * @param allEdges all Edges in LinkedList
     * @return true if there an edge between source and destination,and false if there is not.
     */
    public boolean isConnected(int src, int dest, LinkedList<Edge>[] allEdges) {
        
        for (LinkedList<Edge> i : allEdges) {
            for (Edge edge : i) {
                if ((edge.source == src && edge.destination == dest) || (edge.source == dest && edge.destination == src)) {
                    return true;
                }
            }
        }
        return false;
    }

}

/***
 * <h1>Edge Class</h1>
 * This is the Graph class which has source,destination and weight as instance variable.
 * 
 * @author Rahaf , sarah , Somayah 
 */
class Edge { 

    int source; // integer number represent the source
    int destination; // integer number represent the destination
    int weight; // the weight of edge

    /***
     * This is a Edge constructor that indicates parameters
     * 
     * @param source
     * @param destination
     * @param weight 
     */
    public Edge(int source, int destination, int weight) { // Edge contractor to add new Edge
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

