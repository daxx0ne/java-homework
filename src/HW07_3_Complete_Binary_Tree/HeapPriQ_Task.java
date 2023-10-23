package HW07_3_Complete_Binary_Tree;

/* 완전 이진 트리 구조를 사용하여 힙(Heap) 우선순위 큐를 구현한 클래스 */
public class HeapPriQ_Task extends CompleteBinaryTree<Task> {
    final int CBT_ROOT = 1; // 완전 이진 트리의 루트 인덱스

    public HeapPriQ_Task(String name, int capacity) { // 힙 우선순위 큐를 초기화
        super(name, capacity); // 큐의 이름, 용량
    }

    public boolean isEmpty() { // 큐가 비어있는지 확인
        return this.size == 0;
    }

    public boolean isFull() { // 큐가 다 차있는지 확인
        return this.size >= this.capacity;
    }

    public int size() { // 큐의 사이즈를 반환
        return this.size;
    }

    // 새로운 작업을 큐에 추가하는 메서드
    public int insert(Task ev) {
        int index, prIndex;
        if (isFull()) {
            System.out.printf("%s is full !!\n", this.name);
            return size();
        }
        index = addAtEnd(ev);
        // Up-heap Bubbling: 우선 순위에 따라 적절한 위치에 추가하고, 상위 노드로 올라가면서 힙 조건 유지
        while (index != CBT_ROOT) {
            prIndex = parentIndex(index);
            Task curEv, prEv;
            curEv = (Task) this.genArray[index];
            prEv = (Task) this.genArray[prIndex];
            int curKey, prKey;
            curKey = (int) curEv.getPriority();
            prKey = (int) prEv.getPriority();
            if (curKey >= prKey) {
                break;
            } else {
                this.genArray[prIndex] = curEv;
                this.genArray[index] = prEv;
                index = prIndex;
            }
        }
        this.size++;
        return size();
    }

    // 큐에서 가장 우선순위가 높은 작업을 제거하고 반환하는 메서드
    public Task removeHeapMin() {
        if (this.endIndex == 0) {
            return null;
        }
        Task minEv = (Task) getCBTRoot();
        if (this.size == 1) {
            removeCBTEnd();
        } else {
            Task curEv, chEv, rchEv;
            int curIndex, chIndex, rchIndex;
            int curPri, chPri, rchPri;
            Task temp_ev;
            this.genArray[CBT_ROOT] = this.genArray[this.endIndex];
            curIndex = CBT_ROOT;
            this.endIndex--;

            // Down-heap bubbling: 하위 노드를 끌어올리면서 힙 조건 유지
            while (hasLeftChild(curIndex)) {
                curEv = (Task) this.genArray[curIndex];
                curPri = curEv.getPriority();
                chIndex = leftChildIndex(curIndex);
                rchIndex = rightChildIndex(curIndex);
                chEv = (Task) this.genArray[chIndex];
                chPri = chEv.getPriority();
                if (hasRightChild(curIndex)) {
                    rchEv = (Task) this.genArray[rchIndex];
                    rchPri = rchEv.getPriority();
                    if (chPri > rchPri) {
                        chEv = (Task) this.genArray[rchIndex];
                        chIndex = rchIndex;
                        chPri = rchPri;
                    }
                }
                if (curPri > chPri) {
                    this.genArray[chIndex] = curEv;
                    this.genArray[curIndex] = chEv;
                }
                curIndex = chIndex;
            }
        }
        this.size--;
        return minEv;
    }
}