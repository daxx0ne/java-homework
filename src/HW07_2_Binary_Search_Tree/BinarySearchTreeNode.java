package HW07_2_Binary_Search_Tree;

/* 이진 탐색 트리의 노드를 나타내는 클래스 */
public class BinarySearchTreeNode<E> {
    private E _entry; // 노드에 저장된 엔트리(트리가 정렬된 순서를 유지하는 기준이 됨)
    private BinarySearchTreeNode<E> _parent; // 부모 노드
    private BinarySearchTreeNode<E> _leftChild; // 왼쪽 자식 노드
    private BinarySearchTreeNode<E> _rightChild; // 오른쪽 자식 노드

    // 생성자
    public BinarySearchTreeNode(E entry) {
        this._entry = entry;
        this._parent = null;
        this._leftChild = null;
        this._rightChild = null;
    }

    public E getEntry() { // 노드의 엔트리 값 반환
        return this._entry;
    }

    public void setEntry(E newEntry) { // 노드의 엔트리 값 설정
        this._entry = newEntry;
    }

    public BinarySearchTreeNode<E> getParent() { // 부모 노드 반환
        return this._parent;
    }

    public BinarySearchTreeNode<E> getLeftChild() { // 왼쪽 자식 노드 반환
        return this._leftChild;
    }

    public BinarySearchTreeNode<E> getRightChild() { // 오른쪽 자식 노드 반환
        return this._rightChild;
    }

    public void setParent(BinarySearchTreeNode<E> prBSTN) { // 노드의 부모 설정
        this._parent = prBSTN;
    }

    public void setLeftChild(BinarySearchTreeNode<E> leftChildBSTN) { // 노드의 왼쪽 자식 설정
        this._leftChild = leftChildBSTN;
    }

    public void setRightChild(BinarySearchTreeNode<E> rightChildBSTN) { // 노드의 오른쪽 자식 설정
        this._rightChild = rightChildBSTN;
    }

    // 엔트리 비교하는 메서드
    public int compareKey(E otherEntry) { // 이진 탐색 트리의 노드를 정렬
        int cmpResult = 0;
        if (this._entry instanceof Integer) {
            cmpResult = (int) this._entry - (int) otherEntry;
        } else if (this._entry instanceof Double) {
            cmpResult = Double.compare((double) this._entry, (double) otherEntry);
        } else if (this._entry instanceof String) {
            cmpResult = ((String) this._entry).compareTo((String) otherEntry);
        } else {
            System.out.println("compareKey() is not defined for entry_type E !!");
        }
        return cmpResult;
    }
}