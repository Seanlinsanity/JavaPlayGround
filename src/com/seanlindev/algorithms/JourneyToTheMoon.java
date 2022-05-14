package com.seanlindev.algorithms;

import java.util.ArrayList;
import java.util.List;

public class JourneyToTheMoon {
    /*
     * Complete the 'journeyToMoon' function below.
    *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY astronaut
     */

    //5, [[0,1], [2,3], [0,4]] => ans: 6
    //[0, 1, 4], [2, 3]
    //[0, 2], [0, 3], [1, 2], [1, 3], [4, 2], [4, 3]

    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
        // Write your code here
        Graph graph = new Graph(n);

        for(int i = 0; i < astronaut.size(); i++){
            int source = astronaut.get(i).get(0);
            int destination = astronaut.get(i).get(1);

            graph.addEdge(source,destination);
        }


        boolean[] visited = new boolean[n];
        List<Integer> countries = new ArrayList<Integer>();
        long combinations = 0;

        // store size of each country by traversing each cluster
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                countries.add(graph.dfs(i, visited));
            }
        }

        /* Let size of each country be A, B, C, D ...
         * Combinations: AB + AC + AD + BC + BD + CD + ...
         * => AB + (A+B)C + (A+B+C)D
         */
        int sum = 0;
        for(int country : countries){
            combinations += sum*country;
            sum += country;
        }

        return combinations;
    }

}

class Graph{
    List<Integer>[] vertices;
    public Graph(int count){
        vertices = new ArrayList[count];

        for(int i = 0; i < count; i++){
            vertices[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int source, int destination){
        vertices[source].add(destination);
        vertices[destination].add(source);
    }

    // modified DFS to return number of vertices traversed
    public int dfs(int source, boolean[] visited){
        visited[source] = true;

        int count = 1;

        for(Integer vertex: vertices[source]){
            if(!visited[vertex]){
                count += dfs(vertex, visited);
            }
        }

        return count;
    }
}
