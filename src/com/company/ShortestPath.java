package com.company;
// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph
import javax.xml.transform.Source;
import java.util.*;
import java.lang.*;
import java.io.*;

    class ShortestPath{
        public int V;

        public ShortestPath(int V){
            this.V = V;
        }
        public static void main (String[] args) {
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

                ShortestPath t = new ShortestPath(i);
                t.dijkstra(graph, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }




        }
        // A utility function to find the vertex with minimum distance value,
        int minDistance(int dist[], Boolean sptSet[]) {
            // Initialize min value
            int min = Integer.MAX_VALUE, min_index=-1;

            for (int v = 0; v < V; v++)
                if (sptSet[v] == false && dist[v] <= min) {
                    min = dist[v];
                    min_index = v;
                }

            return min_index;
        }

        // A utility function to print the constructed distance array
        void printSolution(int dist[], int n) {
            for (int i = 0; i < V; i++) {
                String node = "Start";
                switch (i) {
                    case 1:
                        node = "A";
                        break;
                    case 2:
                        node = "B";
                        break;
                    case 3:
                        node = "C";
                        break;
                    case 4:
                        node = "D";
                        break;
                    case 5:
                        node = "E";
                        break;
                    case 6:
                        node = "F";
                        break;
                    case 7:
                        node = "G";
                        break;
                    case 8:
                        node = "H";
                        break;
                    case 9:
                        node = "I";
                }
                System.out.println("Source -> Node: " + node + ": " + dist[i]);
            }
        }

        // Funtion that implements Dijkstra's single source shortest path
        void dijkstra(int graph[][], int src) {
            int dist[] = new int[V]; // The output array. dist[i] will hold
            // the shortest distance from src to i

            // sptSet[i] will true if vertex i is included in shortest
            Boolean sptSet[] = new Boolean[V];

            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
                sptSet[i] = false;
            }

            dist[src] = 0;

            // Find shortest path for all vertices
            for (int count = 0; count < V-1; count++) {

                int u = minDistance(dist, sptSet);

                // Mark the picked vertex as processed
                sptSet[u] = true;

                // Update dist value of the adjacent vertices of the
                // picked vertex.
                for (int v = 0; v < V; v++)


                    if (!sptSet[v] && graph[u][v]!=0 &&
                            dist[u] != Integer.MAX_VALUE &&
                            dist[u]+graph[u][v] < dist[v])
                        dist[v] = dist[u] + graph[u][v];
            }

            printSolution(dist, V);
        }


    }

