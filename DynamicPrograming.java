/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_dynamicprograming;

/**
 *
 * @author TazkiaAp
 */
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


 class Vertex implements Comparable<Vertex>
{
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(String argName) { name = argName; }
    public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}

class Edge
{
    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }
}
public class UAS_DynamicPrograming {
public static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      vertexQueue.add(source);

       while (!vertexQueue.isEmpty()) {
           Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
              if (distanceThroughU < v.minDistance) {
                  vertexQueue.remove(v);
                  v.minDistance = distanceThroughU ;
                  v.previous = u;
                  vertexQueue.add(v);
              }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args)
    {
       Vertex v0 = new Vertex("Burengan");
       Vertex v1 = new Vertex("Gogorante");
       Vertex v2 = new Vertex("Banjaran");
       Vertex v3 = new Vertex("Ngadirejo");
       Vertex v4 = new Vertex("Semampir");
       Vertex v5 = new Vertex("Karangrejo");
       Vertex v6 = new Vertex("Ngasem");
       Vertex v7 = new Vertex("Sambiresik");
       Vertex v8 = new Vertex("Gampeng");
       Vertex v9 = new Vertex("Gampengrejo");
       
       v0.adjacencies = new Edge[]{ new Edge(v1, 3),
                                    new Edge(v2, 2),
                                    new Edge(v3, 3)};
       
       v1.adjacencies = new Edge[]{ new Edge(v0, 3),
                                    new Edge(v4, 3),
                                    new Edge(v5, 5),
                                    new Edge(v6, 7) };
       
       v2.adjacencies = new Edge[]{ new Edge(v0, 2),
                                    new Edge(v4, 5),
                                    new Edge(v5, 4),
                                    new Edge(v6, 4) };
       
       v3.adjacencies = new Edge[]{ new Edge(v0, 3),
                                    new Edge(v4, 5),
                                    new Edge(v5, 3),
                                    new Edge(v6, 4) };
       
       v4.adjacencies = new Edge[]{ new Edge(v1, 3),
                                    new Edge(v2, 5),
                                    new Edge(v3, 5),
                                    new Edge(v7, 6),
                                    new Edge(v8, 7) };
       
       v5.adjacencies = new Edge[]{ new Edge(v1, 5),
                                    new Edge(v2,4 ),
                                    new Edge(v3, 3),
                                    new Edge(v7, 4),
                                    new Edge(v8, 5) };
       
       v6.adjacencies = new Edge[]{ new Edge(v1, 7),
                                    new Edge(v2, 4),
                                    new Edge(v3, 4),
                                    new Edge(v7, 6),
                                    new Edge(v8, 4) };
       
       v7.adjacencies = new Edge[]{ new Edge(v4, 6),
                                    new Edge(v5, 4),
                                    new Edge(v6, 6),
                                    new Edge(v9, 2) };
       
       v8.adjacencies = new Edge[]{ new Edge(v4, 7),
                                    new Edge(v5, 5),
                                    new Edge(v6, 4),
                                    new Edge(v9, 2)};
       
       v9.adjacencies = new Edge[]{ new Edge(v7, 2),
                                    new Edge(v8, 2)};
       Vertex[] vertices = { v0, v1, v2, v3, v4, v5, v6, v7, v8, v9 };
        computePaths(v0);
        for (Vertex v : vertices)
       {
           System.out.println("Distance to " + v + ": " + v.minDistance);
           List<Vertex> path = getShortestPathTo(v);
           System.out.println("Path: " + path);
       }
    }
   }
