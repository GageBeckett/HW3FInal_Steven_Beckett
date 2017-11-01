package com.company;

import javax.swing.text.Style;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    public static void main (String[] args)
    {
        /* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
        try {
            File file = new File("test.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {

                if(!line.isEmpty()) {
                    i++;
                    stringBuffer.append(line);
                    stringBuffer.append("\n");
                }
            }
            fileReader.close();



        MST t = new MST(i);
            String arr = stringBuffer.toString();
            String[] items = arr.replace("\n", "," ).replace(" ", ",").split(",");
            int graph[][]= new int[i][i];
            int count = 0;
            for(int z=0; z < i; z++){
                for(int q=0; q < i; q++){
                    graph[z][q] = Integer.parseInt(items[count]);
                    count++;

                }
            }
/*
            int graph[][] = new int[][] {{0, 4, 0, 0, 0, 8},
                {4, 0, 7, 0, 0, 11},
                {0, 7, 0, 2, 3, 0},
                {0, 0, 2, 0, 6, 7},
                {0, 0, 3, 6, 0, 1},
                {8, 11, 0, 7, 1, 0}
        };
*/

        // Print the solution
        t.primMST(graph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class MST {
    // Number of vertices in the graph
    private int V;
    public MST(int V){
        this.V = V;
    }
    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    void printMST(int parent[], int n, int graph[][]) {
        System.out.println("Node   Weight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "    " +
                    graph[i][parent[i]]);
    }

    // Function to construct and print MST for a graph represented
    //  using adjacency matrix representation
    void primMST(int graph[][]) {
        // Array to store constructed MST
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int[V];

        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0;     // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++)

                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && mstSet[v] == false &&
                        graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        printMST(parent, V, graph);
    }

}
