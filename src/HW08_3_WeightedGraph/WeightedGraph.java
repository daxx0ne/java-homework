package HW08_3_WeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/* 가중 그래프(Weighted Graph)를 표현하는 클래스 */
public class WeightedGraph {
    String name;
    int num_vrtx;
    Vector<Vertex> vrtxArray = new Vector<Vertex>(); // 모든 정점을 저장하는 벡터
    List<WeightedEdge> wedges = new ArrayList<>(); // 가중 간선을 저장하는 리스트
    int[][] distTbl; // 가중치를 저장하는 배열
    ArrayList<List<Vertex>> adjList = new ArrayList<List<Vertex>>(); // 정점의 이웃들을 저장하는 리스트
    boolean dest_arrived = false; // 그래프 검색 알고리즘에서 사용

    public WeightedGraph(String nm) {
        this.name = nm;
    }

    // 주어진 정점 이름으로 해당 정점을 검색 후 반환하는 메서드
    public Vertex findVertex(String vrtxName) {
        String vname;
        for (Vertex v : this.vrtxArray) {
            vname = v.getName();
            if (vname.equals(vrtxName))
                return v;
        }
        return null;
    }

    // 주어진 이름으로 새 정점을 추가한 후 반환하는 메서드
    public Vertex addVertex(String vrtxName) {
        if (this.findVertex(vrtxName) == null) {
            Vertex newVrtx = new Vertex(vrtxName);
            this.vrtxArray.add(newVrtx);
            newVrtx.setVID(this.num_vrtx);
            adjList.add(newVrtx.getVID(), new ArrayList<Vertex>());
            this.num_vrtx++;
            return newVrtx;
        } else {
            return null;
        }
    }

    // 출발, 도착 정점 및 가중치를 사용하여 새로운 가중 간선을 추가하는 메서드
    public void addWeightedEdge(Vertex vrtxSrc, Vertex vrtxDest, int weight) {
        WeightedEdge wedge = new WeightedEdge(vrtxSrc, vrtxDest, weight);
        this.wedges.add(wedge);
        this.adjList.get(vrtxSrc.getVID()).add(vrtxDest);
        wedge = new WeightedEdge(vrtxDest, vrtxSrc, weight);
        this.wedges.add(wedge);
        this.adjList.get(vrtxDest.getVID()).add(vrtxSrc);
    }

    // 그래프의 정점, 인접 리스트를 출력하는 메서드
    public void printWeightedGraph() {
        System.out.print("Vertices (num_vrtx = " + this.num_vrtx + ") : ");
        for (Vertex v : this.vrtxArray) {
            System.out.print(v.getName() + " ");
        }
        System.out.println("\nAdjacency List for the Graph is:");

        for (Vertex v : this.vrtxArray) {
            System.out.print(v.getName() + " -> [ ");
            boolean first = true;
            for (Vertex neighbor : this.adjList.get(v.getVID())) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(neighbor.getName());
                first = false;
            }
            System.out.println(" ]");
        }
    }

    // DFS - 거리 테이블을 초기화하는 메서드
    public void initDistTable() {
        this.distTbl = new int[this.num_vrtx][this.num_vrtx];
        for (int i = 0; i < this.num_vrtx; i++) {
            for (int j = 0; j < this.num_vrtx; j++) {
                if (i == j)
                    this.distTbl[i][j] = 0;
                else
                    this.distTbl[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        for (WeightedEdge e : wedges) {
            int srcVID = e.getSrcVID();
            int destVID = e.getDestVID();
            distTbl[srcVID][destVID] = e.getWeight();

        }
    }

    // DFS - 거리 테이블을 출력하는 메서드
    public void printDistTable() {
        int edge_weight;
        System.out.println("Distance Table:");
        System.out.print("     |");
        for (int i = 0; i < this.num_vrtx; i++) {
            System.out.printf("%7s", this.vrtxArray.get(i).getName());
        }
        System.out.print("\n-----+-----------------------------------------------------------------------------");
        System.out.println();
        for (int i = 0; i < this.num_vrtx; i++) {
            System.out.printf("%5s|", this.vrtxArray.get(i).getName());
            for (int j = 0; j < this.num_vrtx; j++) {
                edge_weight = this.distTbl[i][j];
                if (edge_weight == Integer.MAX_VALUE / 2)
                    System.out.print("     oo");
                else
                    System.out.printf("%7d", edge_weight);
            }
            System.out.println();
        }
        System.out.println();
    }

    // DFS 알고리즘을 구현하는 메서드
    // 시작 정점에서 목표 정점까지 가능한 모든 경로를 탐색하고, 목표 정점에 도달하는 경로를 path 리스트에 저장
    private void _DFS(Vertex vrtx, Vertex dest, List<Vertex> path) { // 현재 정점, 목표 정점, 경로 저장하는 리스트
        path.add(vrtx); // 현재 정점을 경로에 추가
        if (vrtx.equals(dest)) { // 현재 정점이 목표 정점과 일치하는지 확인
            dest_arrived = true;
            return;
        }
        for (Vertex v : this.adjList.get(vrtx.vid)) { // 이웃 노드들을 하나씩 검사
            if (path.contains(v)) // 경로에 v가 이미 추가되어 있는지 확인
                continue;
            if (v.equals(dest)) { // v가 목표 정점과 일치하는지 확인
                path.add(v);
                dest_arrived = true;
                break; // 일치 하면 방문 표시와 함께 메서드 종료
            }
            if (!dest_arrived) { // _DFS를 재귀호출하여 목표 정점을 찾을 때까지 계속해서 탐색
                _DFS(v, dest, path);
                if (!dest_arrived) {
                    path.remove(path.size() - 1);
                }
            }
            if (dest_arrived)
                break;
        }
    }

    // DFS 알고리즘을 활용하여 두 개의 정점 사이에 있는 경로를 찾는 메서드
    public List<Vertex> DepthFirstSearch(Vertex vStart, Vertex vEnd) {
        List<Vertex> path = new ArrayList<>();
        dest_arrived = false;
        _DFS(vStart, vEnd, path); // _DFS를 통해 찾은 경로를 경로 리스트로 반환
        return path;
    }

    // BFS 알고리즘을 구현하는 메서드
    // 목표 정점 dest부터 시작하여 역순으로 정점을 탐색하여 최단 경로를 찾아낸 후 path 리스트에 저장 후 반환
    public List<Vertex> BreadthFirstSearch(Vertex vStart, Vertex dest) { // 시작 정점, 목표 정점
        for (Vertex v : this.vrtxArray) { // 방문 여부, 이전 정점, 레벨을 초기화
            v.visited = false;
            v.prev = null;
            v.level = -1;
        }
        vStart.level = 0; // 해당 정점이 시작 정점에서 얼마나 떨어져 있는지 나타내는 값
        vStart.visited = true; // 방문 플래그
        vStart.prev = vStart;
        int num_visited = 1; // 시작 정점을 방문했으므로 1로 설정
        int searchLevel = 0; // 현재 탐색 중인 레벨
        while (num_visited < num_vrtx) { // 정점의 총 수에 도달할 때까지 반복
            for (Vertex v : this.vrtxArray) {
                if ((v.visited) && (v.level == searchLevel)) { // searchLevel 에 있는 모든 방문한 정점을 순회하면서 이웃 정점을 탐색
                    for (Vertex w : this.adjList.get(v.vid)) {
                        if (!w.visited) {
                            w.visited = true; // 이웃 정점에 방문 표시
                            w.level = searchLevel + 1;
                            w.prev = v;
                            num_visited++; // 방문한 정점의 수 업데이트
                        }
                    }
                }
            }
            searchLevel++;
        }
        List<Vertex> path = new ArrayList<>();
        Vertex v = dest;
        while (!v.equals(vStart)) {
            path.add(0, v);
            v = v.prev;
        }
        path.add(0, v);

        StringBuilder pathString = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            pathString.append(path.get(i).getName());
            if (i != path.size() - 1) {
                pathString.append("->");
            }
        }
        return path;
    }

    // Dijkstra 알고리즘을 구현하는 메서드
    // 가중치가 있는 그래프에서 최단 경로를 찾을 때 적용
    public List<Vertex> DijkstraSortestPath(Vertex vStart, Vertex dest) {
        ArrayList<Vertex> path = new ArrayList<>();
        if (dest.equals(vStart)) { // 시작 정점과 목표 정점이 같은, 이미 도착한 경우에는
            path.add(dest); // 목표 정점을 path 리스트에 추가하고
            return path; // 반환함
        }
        ArrayList<Vertex> selectedVrtxs = new ArrayList<>(); // 최단 경로를 이미 찾은 정점 리스트
        ArrayList<Vertex> remainVrtxs = new ArrayList<>(); // 아직 최단 경로를 못 찾은 정점 리스트
        int vid_start = vStart.vid; // 시작 정점을 selectedVrtxs 리스트에 추가
        int minAccDist, vAccDist;
        int minVID;
        Vertex minVrtx = null;
        for (Vertex v : vrtxArray) {
            if (v.equals(vStart)) {
                selectedVrtxs.add(v);
                v.visited = true;
                v.accDist = 0; // 누적 거리
                v.prev = vStart; // 이전 정점을 시작 정점으로 설정
            } else { // 그 외의 모든 정점은 방문하지 않는 상태로 설정
                remainVrtxs.add(v);
                v.visited = false;
                v.accDist = this.distTbl[vid_start][v.vid]; // 시작 정점에서 해당 정점까지의 거리로 초기화
                v.prev = vStart;
            }
        }
        while (!remainVrtxs.isEmpty()) { // 아직 최단 경로를 못 찾은 정점이 없을 때까지 반복
            minAccDist = Integer.MAX_VALUE / 2;
            minVID = -1;
            minVrtx = null;
            for (Vertex v : remainVrtxs) { // 가장 작은 누적 거리를 가진 정점을 선택함
                vAccDist = v.accDist;
                if (vAccDist < minAccDist) {
                    minVID = v.vid;
                    minAccDist = vAccDist;
                    minVrtx = v;
                }
            }
            if (minVID == -1) {
                System.out.println("Error in selection of vertex with minimum AccDist");
                System.out.println("Graph is not fully connected !!");
                break;
            }
            selectedVrtxs.add(minVrtx); // 선택한 정점을 selectedVrtxs 리스트에 추가하고
            remainVrtxs.remove(minVrtx); // remainVrtxs 리스트에서 제거
            minAccDist = minVrtx.accDist;
            for (Vertex v : remainVrtxs) {
                if (v.accDist > (minAccDist + distTbl[minVID][v.vid])) {
                    v.accDist = minAccDist + distTbl[minVID][v.vid];
                    v.prev = minVrtx;
                }
            }
            if (minVrtx.equals(dest)) {
                break;
            }
        }
        path.clear();
        path.add(dest);
        Vertex v = dest;
        while (!v.equals(vStart)) { // 목표 정점부터 시작해서 역순으로 최단 경로를 찾고 해당 경로를 반환
            v = v.prev;
            path.add(0, v);
        }
        return path;
    }
}
