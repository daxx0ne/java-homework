/*
 * Homework 8.3 그래프 자료구조와 알고리즘, 파일 입출력
 * 이름: 박다원
 * 학번: 21912154
 */

package HW08_3_WeightedGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class App_WeightedGraph {
    public static void main(String args[]) throws FileNotFoundException {
        final String fname = "/Users/daone/IdeaProjects/java-homework/src/HW08_3_WeightedGraph/KR_11_cities.txt";
        Scanner fin = new Scanner(new File(fname));
        String src, dest;
        int weight;
        WeightedGraph wgraph = new WeightedGraph("WeightedGraph - KR (11 Cities)");
        Vertex vrtx, vrtx_src, vrtx_dest;
        while (fin.hasNext()) {
            src = fin.next();
            dest = fin.next();
            weight = fin.nextInt();
            if (wgraph.findVertex(src) == null) {
                wgraph.addVertex(src);
            }
            vrtx_src = wgraph.findVertex(src);
            if (wgraph.findVertex(dest) == null) {
                wgraph.addVertex(dest);
            }
            vrtx_dest = wgraph.findVertex(dest);
            wgraph.addWeightedEdge(vrtx_src, vrtx_dest, weight);
        }
        fin.close();

        wgraph.printWeightedGraph();
        wgraph.initDistTable();
        wgraph.printDistTable();
        List<Vertex> path;
        Vertex vStart = wgraph.findVertex("GJ");
        Vertex vEnd = wgraph.findVertex("SC");
        System.out.printf("DepthFirstSearch (%s -> %s) : ", vStart.getName(), vEnd.getName());
        path = wgraph.DepthFirstSearch(vStart, vEnd);

        printPath(path);
        System.out.printf("DepthFirstSearch (%s -> %s) : ", vEnd.getName(), vStart.getName());
        path = wgraph.DepthFirstSearch(vEnd, vStart);

        printPath(path);
        System.out.printf("BreadthFirstSearch (%s -> %s) : ", vStart.getName(), vEnd.getName());
        path = wgraph.BreadthFirstSearch(vStart, vEnd);

        printPath(path);
        System.out.printf("BreadthFirstSearch (%s -> %s) : ", vEnd.getName(), vStart.getName());
        path = wgraph.BreadthFirstSearch(vEnd, vStart);

        printPath(path);
        System.out.printf("Dijkstra ShortestPathFirst (%s -> %s) : ", vStart.getName(), vEnd.getName());
        path = wgraph.DijkstraSortestPath(vStart, vEnd);

        printPath(path);
        System.out.printf("Dijkstra ShortestPathFirst (%s -> %s) : ", vEnd.getName(), vStart.getName());
        path = wgraph.DijkstraSortestPath(vEnd, vStart);

        printPath(path);
    }

    public static void printPath(List<Vertex> path) {
        for (int i = 0; i < path.size(); i++) {
            Vertex v = path.get(i);
            System.out.printf("%s", v.getName());
            if (i != (path.size() - 1))
                System.out.print("->");
        }
        System.out.println();
    }
}
