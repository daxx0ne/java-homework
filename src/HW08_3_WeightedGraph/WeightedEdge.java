package HW08_3_WeightedGraph;

/* 간선(Weighted Edge)을 나타내는 클래스 */
public class WeightedEdge {
    Vertex src; // 출발 정점
    Vertex dest; // 도착 정점
    int weight; // 가중치

    WeightedEdge(Vertex src, Vertex dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return (this.src + "->" + this.dest);
    }

    int getSrcVID() {
        return this.src.vid;
    }

    int getDestVID() {
        return this.dest.vid;
    }

    Vertex getDest() {
        return this.dest;
    }

    int getWeight() {
        return this.weight;
    }
}
