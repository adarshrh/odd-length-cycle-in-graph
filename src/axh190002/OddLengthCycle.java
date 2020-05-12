/**
 * IDSA Short Project 6
 * Team members:
 * Adarsh Raghupati   axh190002
 * Keerti Keerti      kxk190012
 */

package axh190002;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class OddLengthCycle {

    public static final int INFINITY = Integer.MAX_VALUE;

    /**
     * Finds odd length cycle in the graph by running BFS on each component.
     * @param g
     * @return List of vertices which forms the odd length cycle. NULL if graph is bipartite
     */
    public static List<Graph.Vertex> oddCycle(Graph g){
        Set<Integer> visitedSet = new HashSet<>();
        List<Graph.Vertex> vertexList = new ArrayList<>();
        for (Graph.Vertex u : g){
         //If the vertex is not visited run BFS starting from this vertex
            if(!visitedSet.contains(u.name)){
              vertexList = findOddLenCycleWithSource(g,u.name,visitedSet);
              if(vertexList.size()>0){
                  break;
              }
            }
        }
        if(vertexList.size()==0){
            System.out.println("Graph is bipartite. There is no odd length cycle in the graph");
            return null;
        }
        else printOddLengthCycle(vertexList);

        return vertexList;
    }

    /**
     * Calls BFS algorithm starting from the given source and finds the odd length cycle in the current component
     * Returns empty list if there is no odd length cycle in current component
     * @param g Input graph
     * @param source Starting point of BFS
     * @param visitedSet This set contains vertices which are already processed by the BFS algorithm
     * @return List of vertices which forms the odd length cycle.
     */
    private static List<Graph.Vertex> findOddLenCycleWithSource(Graph g, int source, Set<Integer> visitedSet) {
        BFSOO b = BFSOO.breadthFirstSearch(g, source);
        boolean oddCycle = false;
        Graph.Vertex node1 = null;
        Graph.Vertex node2 = null;

        for (Graph.Vertex u : g) {
            if (oddCycle)
                break;
            //If the distance of vertex is infinity then this vertex does not belong to current component
            if (b.getDistance(u) == INFINITY)
                continue;
            //Mark the node as visited
            visitedSet.add(u.name);
            Iterable<Graph.Edge> edges = g.outEdges(u);
            Iterator iterator = edges.iterator();
            while (iterator.hasNext()) {
                Graph.Edge edge = (Graph.Edge) iterator.next();
                //Mark the from and to node as visited
                visitedSet.add(edge.from.name);
                visitedSet.add(edge.to.name);
                if (b.getDistance(edge.from) == b.getDistance(edge.to)) {
                    oddCycle = true;
                    node1 = edge.from;
                    node2 = edge.to;
                    break;
                }
            }
        }
        //Finding least common ancestor of the two nodes
        List<Graph.Vertex> list1 = new ArrayList<>();
        List<Graph.Vertex> list2 = new ArrayList<>();
        if (oddCycle) {
            while (node1.name != node2.name) {
                list1.add(node1);
                list2.add(node2);
                node1 = b.getParent(node1);
                node2 = b.getParent(node2);
            }
            //Reverse the list2 and add to list1 to get the vertices in the order
            list2.add(node1);
            Collections.reverse(list2);
            list1.addAll(list2);

        }
        return list1;
    }

    /**
     * Prints the vertices involved in odd length cycle
     * @param vertexList
     */
    public static void printOddLengthCycle(List<Graph.Vertex> vertexList){
    System.out.println("Vertices included in odd length cycle are:");
        boolean flag = true;
        for (Graph.Vertex v: vertexList){
            if(flag){
                System.out.print(v.name);
                flag = false;
            }
            else
                System.out.print(","+v.name);
        }
        System.out.println();
    }
    public static void main(String[] args) throws FileNotFoundException {

        String string = "8 9   1 3 3   2 4 5   3 4 4   4 5 1   6 7 -1   7 6 -1   8 7 1   8 6 1   1 2 2 1";
        Scanner in;
        // If there is a command line argument, use it as file from which
        // input is read, otherwise use input from string.
        in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
        // Read graph from input
        Graph g = Graph.readGraph(in,false);
        oddCycle(g);
        in.close();
    }
}
