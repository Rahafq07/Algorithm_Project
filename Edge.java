

/**
 * <h1>Edge Class</h1>
 * This is the Graph class which has source,destination and weight as instance variable.
 * 
 * @author Rahaf , sarah , Somayah 
 * @version IDE 8.2
 * @since 11-11-2021
 */

public class Edge { 

    int source; // integer number represent the source
    int destination; // integer number represent the destination
    int weight; // the weight of edge

    /**
     * This is a Edge constructor that indicates parameters
     * 
     * @param source source vertex.
     * @param destination destination vertex.
     * @param weight weight of edge between source and destination.
     */
    public Edge(int source, int destination, int weight) { // Edge contractor to add new Edge
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}
