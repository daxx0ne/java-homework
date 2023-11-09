package HW08_3_WeightedGraph;

/* 정점(Vertex)을 나타내는 클래스 */
public class Vertex {
    String vname; // 정점의 이름
    int vid; // 정점 ID
    boolean visited; // 정점의 방문 여부
    Vertex prev; // 정점에 도달하기 전의 정점
    int accDist; // 출발 지점으로부터 해당 정점까지의 누적 거리
    int level; // 정점의 깊이 - BFS 에서 사용

    Vertex(String name) {
        this.vname = name;
        this.visited = false;
        this.prev = null;
        this.accDist = 0;
    }

    public String getName() {
        return this.vname;
    }

    public String toString() {
        return this.vname;
    }

    public void setVID(int numVrtx) {
        this.vid = numVrtx;
    }

    public int getVID() {
        return this.vid;
    }
}
