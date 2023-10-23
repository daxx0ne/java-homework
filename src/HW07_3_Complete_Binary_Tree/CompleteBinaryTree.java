package HW07_3_Complete_Binary_Tree;

/* class GenericArray<E>를 상속받으며, 우선 순위 큐를 구현하기 위한 기본 기능을 제공하는 클래스 */
public class CompleteBinaryTree<E> extends GenericArray<E> {
    String name;
    int CBT_capacity;
    int endIndex;

    public CompleteBinaryTree(String name, int capacity) { // 트리의 이름과 용량을 받아서 초기화
        super(capacity + 1);
        this.name = name; // 트리 이름
        this.CBT_capacity = capacity; // 용량
        this.endIndex = 0; // 끝 인덱스
    }

    // 주어진 엔트리를 트리의 끝에 추가하는 메서드
    public int addAtEnd(E entry) {
        if (this.endIndex >= this.CBT_capacity) { // 용량 보다 인덱스가 크거나 같을 때
            System.out.printf("%s is full now !!", this.name); // 다 차 있음을 알림
            return -1;
        }
        this.endIndex++;
        this.genArray[this.endIndex] = entry; // 엔트리를 배열에 저장
        return this.endIndex;
    }

    // 트리의 루트 노드(1번 인덱스)에 있는 엔트리를 반환하는 메서드
    public E getCBTRoot() {
        E entry = (E) this.genArray[1];
        return entry;
    }

    // 트리의 끝에 있는 엔트리를 제거하는 메서드
    public void removeCBTEnd() {
        this.endIndex--;
        this.size--;
    }

    // 부모 인덱스 계산 및 반환하는 메서드
    protected int parentIndex(int index) {
        return index / 2;
    }

    // 왼쪽 자식 인덱스 계산 및 반환하는 메서드
    protected int leftChildIndex(int index) {
        return index * 2;
    }

    // 오른쪽 자식 인덱스 계산 및 반환하는 메서드
    protected int rightChildIndex(int index) {
        return index * 2 + 1;
    }

    // 왼쪽 자식 여부를 확인하는 메서드
    protected boolean hasLeftChild(int index) {
        return (index * 2) <= endIndex;
    }

    // 오른쪽 자식 여부를 확인하는 메서드
    protected boolean hasRightChild(int index) {
        return (index * 2 + 1) <= endIndex;
    }
}