package HW07_2_Binary_Search_Tree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/* 이진 탐색 트리(Binary Search Tree, BST)를 구현한 클래스 */
public class BalancedBinarySearchTree<E> {
    private String _name;
    private BinarySearchTreeNode<E> _root;
    private int _numEntry;

    public BalancedBinarySearchTree(String bst_nm) {
        this._name = bst_nm;
        this._root = null;
        this._numEntry = 0;
    }

    // 이진 탐색 트리의 노드 개수 반환
    public int size() {
        return this._numEntry;
    }

    // 이진 탐색 트리가 비어 있는지 확인
    public boolean isEmpty() {
        return (this._numEntry == 0);
    }

    // 현재 이진 탐색 트리의 루트 노드 반환
    public BinarySearchTreeNode<E> getRoot() {
        return this._root;
    }

    // Insert: 새로운 엔트리를 이진 탐색 트리에 추가
    private BinarySearchTreeNode<E> _insertInOrder(BinarySearchTreeNode<E> subRoot_bstn, BinarySearchTreeNode<E> pr_bstn, E newEntry) { // 매개 변수: 현재 서브트리의 루트 노드, 부모 노드, 추가하려는 새로운 엔트리
        BinarySearchTreeNode<E> new_bstn, bstn = null, leftChild, rightChild;
        if (subRoot_bstn == null) { // 노드가 null 일 때
            new_bstn = new BinarySearchTreeNode<E>(newEntry); // 새로운 엔트리를 추가하기 위한 새로운 노드인 new_bstn을 생성
            if (this._numEntry == 0) // 이진 탐색 트리가 비어 있는 경우 _root로 설정
                this._root = new_bstn;
            new_bstn.setLeftChild(null); // 왼, 오른쪽 자식을 갖지 않도록 설정
            new_bstn.setRightChild(null);
            this._numEntry++;
            return new_bstn;
        } else {  // 노드가 null이 아닐 때
            // subRoot_bstn 노드와 비교 후 새로운 엔트리인 newEntry의 크기에 따라 왼쪽 or 오른쪽 서브트리로 이동
            if (subRoot_bstn.compareKey(newEntry) > 0) { // subRoot_bstn 노드의 키가 newEntry보다 큰 경우
                leftChild = subRoot_bstn.getLeftChild(); // 왼쪽으로 이동
                bstn = _insertInOrder(leftChild, subRoot_bstn, newEntry);
                if (bstn != null) {
                    subRoot_bstn.setLeftChild(bstn);
                    bstn.setParent(subRoot_bstn);
                }
                return null;
            } else if (subRoot_bstn.compareKey(newEntry) < 0) { // subRoot_bstn 노드의 키가 newEntry보다 작은 경우
                rightChild = subRoot_bstn.getRightChild(); // 오른쪽으로 이동
                bstn = _insertInOrder(rightChild,
                        subRoot_bstn, newEntry);
                if (bstn != null) {
                    subRoot_bstn.setRightChild(bstn);
                    bstn.setParent(subRoot_bstn);
                }
                return null;
            } else { // subRoot_bstn 노드의 키가 newEntry와 같은 경우
                subRoot_bstn.setEntry(newEntry);
                bstn = subRoot_bstn;
                return null;
            }
        }
    }

    // _insertOrder 메서드를 호출하여 이진 탐색 트리에 새로운 엔트리를 추가하고, 추가된 노드를 반환
    public BinarySearchTreeNode<E> insert(E newEntry) {
        BinarySearchTreeNode<E> bstn;
        bstn = _insertInOrder(this._root, null, newEntry);
        return bstn;
    }

    // search: 재귀적으로 검색을 하면서 원하는 노드를 찾을 수 있음
    public BinarySearchTreeNode<E> _search(BinarySearchTreeNode<E> subRoot, E searchKey) { // 매개변수: 현재 서브트리의 루트 노드, 검색하려는 키
        BinarySearchTreeNode<E> bstn_result = null;
        if (subRoot == null) // 검색 실패
            return null;
        if (subRoot.compareKey(searchKey) == 0) // 검색 성공
            return subRoot;
        else if (subRoot.compareKey(searchKey) > 0) { // 왼쪽 서브트리에서 재귀 검색
            bstn_result = _search(subRoot.getLeftChild(), searchKey);
            return bstn_result; // 해당 노드 반환
        } else { // 오른쪽 서브트리에서 재귀 검색
            bstn_result = _search(subRoot.getRightChild(), searchKey);
            return bstn_result;
        }
    }

    // _search 메서드를 호출하여 특정 키를 찾아서 해당 노드 반환
    public BinarySearchTreeNode<E> search(E searchKey) {
        BinarySearchTreeNode<E> bstn_result = null;
        bstn_result = _search(this._root, searchKey);
        return bstn_result;
    }

    // _findMin: 가장 작은 엔트리를 가진 노드를 찾아서 반환
    public BinarySearchTreeNode<E> _findMin(BinarySearchTreeNode<E> bstn) {
        if (bstn.getLeftChild() != null) {
            bstn = bstn.getLeftChild();
            while (bstn.getLeftChild() != null) {
                bstn = bstn.getLeftChild();
            }
        }
        return bstn;
    }

    // _findMax: 가장 큰 엔트리를 가진 노드를 찾아서 반환
    public BinarySearchTreeNode<E> _findMax(BinarySearchTreeNode<E> bstn) {
        if (bstn.getRightChild() != null) {
            bstn = bstn.getRightChild();
            while (bstn.getRightChild() != null) {
                bstn = bstn.getRightChild();
            }
        }
        return bstn;
    }

    // 중위 순회 방식으로 전체 이진 탐색 트리를 순회하고 엔트리를 배열에 추가
    public void _inOrderTraversal(BinarySearchTreeNode<E> curBSTN, ArrayList<E> array_values) {
        if (curBSTN.getLeftChild() != null) { // 현재 노드: curBSTN 이 왼쪽 자식 노드를 가지고 있으면
            _inOrderTraversal(curBSTN.getLeftChild(), array_values); // 왼쪽 서브트리로 이동하여 _inOrderTraversal 메서드 재귀 호출
        }
        array_values.add(curBSTN.getEntry()); // 현재 노드의 엔트리를 배열에 추가
        if (curBSTN.getRightChild() != null) {
            _inOrderTraversal(curBSTN.getRightChild(), array_values);
        }
    }

    // 엔트리를 문자열로 반환
    public String toString() {
        ArrayList<E> array_values = new ArrayList<E>();
        StringBuilder str = new StringBuilder(String.format("%s : ", this._name));
        _inOrderTraversal(this._root, array_values);
        for (E entry : array_values) {
            str.append(entry).append(" ");
        }
        return str.toString();
    }

    // 가장 긴 서브트리의 높이를 반환
    public int _getHeight(BinarySearchTreeNode<E> bstn) {
        int height = 0;
        int height_Lc, height_Rc;
        if (bstn != null) { // 해당 노드의 왼쪽 서브트리와 오른쪽 서브트리의 높이를 재귀적으로 계산
            height_Lc = _getHeight(bstn.getLeftChild());
            height_Rc = _getHeight(bstn.getRightChild());
            if (height_Lc > height_Rc)
                height = 1 + height_Lc;
            else
                height = 1 + height_Rc;
        }
        return height;
    }

    // 서브트리 높이 차이를 계산하여 반환
    public int _getHeightDiff(BinarySearchTreeNode<E> bstn) {
        int heightDiff = 0;
        if (bstn != null) { // 왼쪽, 오른쪽 서브트리의 높이를 계산하고 그 차이를 계산
            heightDiff = _getHeight(bstn.getLeftChild()) - _getHeight(bstn.getRightChild());
        }
        return heightDiff; // 음수가 나오면 왼쪽 서브트리가 더 높고, 양수가 나오면 오른쪽 서브트리가 더 높은 경우로 판단할 수 있음
    }

    // _deleteBSTN: 주어진 노드를 이진 탐색 트리에서 삭제, 균형 맞추기
    public BinarySearchTreeNode<E> _deleteBSTN(BinarySearchTreeNode<E> toBeDeleted) {
        BinarySearchTreeNode<E> newSubRoot = null, temp, w, wLc;
        if (toBeDeleted == null)
            return null;
        if (toBeDeleted.getLeftChild() == null && toBeDeleted.getRightChild() == null) { // 양쪽 자식이 모두 없는 경우
            newSubRoot = null; // 해당 위치를 null로 설정
        } else if (toBeDeleted.getLeftChild() != null && toBeDeleted.getRightChild() == null) { // 왼쪽 자식만 있는 경우
            newSubRoot = toBeDeleted.getLeftChild(); // 자식을 새로운 서브루트로 설정
            newSubRoot.setParent(toBeDeleted.getParent());
        } else if (toBeDeleted.getLeftChild() == null && toBeDeleted.getRightChild() != null) { // 오른쪽 자식만 있는 경우
            newSubRoot = toBeDeleted.getRightChild();
            newSubRoot.setParent(toBeDeleted.getParent());
        } else { // 양쪽 자식이 모두 있는 경우
            int heightDiff = _getHeightDiff(toBeDeleted); // 왼쪽, 오른쪽 서브트리의 높이 차이를 계산
            BinarySearchTreeNode<E> lChild = toBeDeleted.getLeftChild();
            BinarySearchTreeNode<E> rChild = toBeDeleted.getRightChild();
            BinarySearchTreeNode<E> parToBeDeleted = toBeDeleted.getParent();
            BinarySearchTreeNode<E> ioPd = null, lcIoPd, parIoPd;
            BinarySearchTreeNode<E> ioSs = null, rcIoSs, parIoSs;
            if (heightDiff > 0) { // 왼쪽 서브트리가 더 높은 경우
                ioPd = _findMax(toBeDeleted.getLeftChild()); // 왼쪽 서브트리에서 가장 큰 노드를 찾음
                lcIoPd = ioPd.getLeftChild();
                parIoPd = ioPd.getParent();
                if (ioPd.getParent() != toBeDeleted) {
                    ioPd.setLeftChild(lChild);
                    parIoPd.setRightChild(lcIoPd);
                    if (lcIoPd != null)
                        lcIoPd.setParent(parIoPd);
                }
                ioPd.setRightChild(rChild);
                ioPd.setParent(parToBeDeleted);
                newSubRoot = ioPd; // 새로운 서브루트
            } else { // 오른쪽 서브트리가 더 높은 경우
                ioSs = _findMin(toBeDeleted.getRightChild()); // 오른쪽 서브트리에서 가장 작은 노드를 찾음
                rcIoSs = ioSs.getRightChild();
                parIoSs = ioSs.getParent();
                if (parIoSs != toBeDeleted) {
                    ioSs.setRightChild(rChild);
                    parIoSs.setLeftChild(rcIoSs);
                    if (rcIoSs != null)
                        rcIoSs.setParent(parIoSs);
                }
                ioSs.setLeftChild(lChild);
                ioSs.setParent(parIoSs);
                newSubRoot = ioSs; // 새로운 서브 루트
            }
            if (lChild != ioPd)
                lChild.setParent(newSubRoot);
            if (rChild != ioSs)
                rChild.setParent(newSubRoot);
        }
        if (toBeDeleted == this._root) // 왼, 오른쪽 자식의 부모 정보를 업데이트 한 후, toBeDeleted가 루트 노트라면
            this._root = newSubRoot; // 새로운 서브루트를 _root로 설정하고
        this._numEntry--; // 트리의 엔트리 수를 하나 줄임
        return newSubRoot;
    }

    // 회전 연산: 주어진 노드를 (LL) 회전
    // 왼쪽 자식의 왼쪽 서브트리가 높이가 더 높아져서 균형이 깨지게 된 경우
    // 주어진 노드를 오른쪽으로 회전 시킴
    public BinarySearchTreeNode<E> _rotateLL(BinarySearchTreeNode<E> curSubRoot) {
        BinarySearchTreeNode<E> newSubRoot, BRC, curParent;
        curParent = curSubRoot.getParent();
        newSubRoot = curSubRoot.getLeftChild();
        BRC = newSubRoot.getRightChild();
        curSubRoot.setLeftChild(BRC);
        if (BRC != null)
            BRC.setParent(curSubRoot);
        newSubRoot.setRightChild(curSubRoot);
        newSubRoot.setParent(curParent);
        curSubRoot.setParent(newSubRoot);
        return newSubRoot;
    }

    // 회전 연산: 주어진 노드를 (RR) 회전
    // 오른쪽 자식의 오른쪽 서브트리가 높이가 더 높아져서 균형이 깨지게 된 경우
    // 주어진 노드를 왼쪽으로 회전 시킴
    public BinarySearchTreeNode<E> _rotateRR(BinarySearchTreeNode<E> curSubRoot) {
        BinarySearchTreeNode<E> newSubRoot, BLC, curParent;
        curParent = curSubRoot.getParent();
        newSubRoot = curSubRoot.getRightChild();
        BLC = newSubRoot.getLeftChild();
        curSubRoot.setRightChild(BLC);
        if (BLC != null)
            BLC.setParent(curSubRoot);
        newSubRoot.setLeftChild(curSubRoot);
        newSubRoot.setParent(curParent);
        curSubRoot.setParent(newSubRoot);
        return newSubRoot;
    }

    // 회전 연산: 주어진 노드를 (LR) 회전
    // 왼쪽 자식의 오른쪽 서브트리가 높이가 더 높아져서 균형이 깨지게 된 경우
    // 왼쪽 자식을 RR 회전 후 주어진 노드를 LL 회전 시킴
    public BinarySearchTreeNode<E> _rotateLR(BinarySearchTreeNode<E> curSubRoot) {
        BinarySearchTreeNode<E> subRoot, newSubRoot, A, B, C, BL, BR, curParent;
        C = curSubRoot;
        curParent = curSubRoot.getParent();
        A = C.getLeftChild();
        B = A.getRightChild();
        BL = B.getLeftChild();
        BR = B.getRightChild();

        subRoot = _rotateRR(A);
        newSubRoot = _rotateLL(C);
        newSubRoot.setParent(curParent);
        A.setParent(newSubRoot);
        C.setParent(newSubRoot);
        if (BL != null)
            BL.setParent(A);
        if (BR != null)
            BR.setParent(C);
        return newSubRoot;
    }

    // 회전 연산: 주어진 노드를 (RL) 회전
    // 오른쪽 자식의 왼쪽 서브트리가 높이가 더 높아져서 균형이 깨지게 된 경우
    // 왼쪽 자식을 LL 회전 후 주어진 노드를 RR 회전 시킴
    public BinarySearchTreeNode<E> _rotateRL(BinarySearchTreeNode<E> curSubRoot) {
        BinarySearchTreeNode<E> subRoot, newSubRoot, A, B, C, BL, BR, curParent;
        A = curSubRoot;
        curParent = curSubRoot.getParent();
        C = A.getRightChild();
        B = A.getLeftChild();
        BL = B.getLeftChild();
        BR = B.getRightChild();

        subRoot = _rotateLL(C);
        newSubRoot = _rotateRR(A);
        newSubRoot.setParent(curParent);
        A.setParent(newSubRoot);
        C.setParent(newSubRoot);
        if (BL != null)
            BL.setParent(A);
        if (BR != null)
            BR.setParent(C);

        return newSubRoot;
    }

    // 균형을 맞추기 위해서 위 4가지 회전 연산 중 적절한 것을 선택하고 실행함
    public BinarySearchTreeNode<E> _reBalance(BinarySearchTreeNode<E> curSubRoot) {
        BinarySearchTreeNode<E> newSubRoot = null;
        int heightDiff = 0;
        heightDiff = _getHeightDiff(curSubRoot);
        if (heightDiff > 1) { // left subtree is higher
            if (_getHeightDiff(curSubRoot.getLeftChild()) > 0) {
                newSubRoot = _rotateLL(curSubRoot);
            } else {
                newSubRoot = _rotateLR(curSubRoot);
            }
        } else if (heightDiff < -1) {
            if (_getHeightDiff(curSubRoot.getRightChild()) < 0) {
                newSubRoot = _rotateRR(curSubRoot);
            } else {
                newSubRoot = _rotateRL(curSubRoot);
            }
        }
        return newSubRoot;
    }

    // 새로운 엔트리를 이진 탐색 트리에 추가하고, 회전 연산 수행
    public void insert_withRebalancing(E newEntry) {
        BinarySearchTreeNode<E> newSubRoot;
        newSubRoot = insert_withRebalancing(this._root, null, newEntry);
        if (newSubRoot != null)
            this._root = newSubRoot;
    }

    public BinarySearchTreeNode<E> insert_withRebalancing(BinarySearchTreeNode<E> curSubRoot, BinarySearchTreeNode<E> curParent, E newEntry) {
        BinarySearchTreeNode<E> newBSTN, bstn, LC, RC;
        if (curSubRoot == null) { // 트리에 더 이상 노드가 없는 경우
            newBSTN = new BinarySearchTreeNode<E>(newEntry); // 새로운 BinarySearchTreeNode를 생성
            curSubRoot = newBSTN; // 위 노드를 현재 위치에 할당
            if (curParent != null)
                newBSTN.setParent(curParent); // 부모 노드 설정
            newBSTN.setLeftChild(null);
            newBSTN.setRightChild(null);
            this._numEntry++;
            return curSubRoot;
        }
        E bstn_entry = curSubRoot.getEntry();
        // curSubRoot와 newEntry의 키를 비교하여, newEntry를 왼쪽 서브트리 or 오른쪽 서브트리로 삽입할지 위치를 결정함
        if (curSubRoot.compareKey(newEntry) > 0) {
            LC = curSubRoot.getLeftChild();
            bstn = insert_withRebalancing(LC, curSubRoot, newEntry);
            if (bstn != null) {
                curSubRoot.setLeftChild(bstn);
                curSubRoot = _reBalance(curSubRoot);
            }
        } else {
            RC = curSubRoot.getRightChild();
            bstn = insert_withRebalancing(RC, curSubRoot, newEntry);
            if (bstn != null) {
                curSubRoot.setRightChild(bstn);
                curSubRoot = _reBalance(curSubRoot);
            }
        }
        return curSubRoot; // 재귀 호출 끝날 때까지 트리에 엔트리를 추가
    }


    // 이진 탐색 트리를 파일에 출력
    public void fprintBST_withDepth(FileWriter fout) throws IOException {
        if (_numEntry == 0) {
            fout.write("BinarySearchTree (" + _name + ") is Empty !!\n"); // _numEntry가 0일 때는 이진 탐색 트리가 비어 있는 경우
        } else {
            fout.write("BinarySearchTree (" + _name + ", num_entry=" + _numEntry + ") : \n");
            fprintBST(fout, _root, 0);
        }
    }

    private void fprintBST(FileWriter fout, BinarySearchTreeNode<E> node, int depth) throws IOException {
        if (node != null) {
            // 오른쪽 서브트리 출력
            fprintBST(fout, node.getRightChild(), depth + 1);

            // 들여쓰기
            for (int i = 0; i < depth; i++) {
                fout.write("   ");
            }

            fout.write(node.getEntry().toString() + "\n");

            // 왼쪽 서브트리 출력
            fprintBST(fout, node.getLeftChild(), depth + 1);
        }
    }

    // 루트 노드 설정
    public void setRoot(BinarySearchTreeNode<E> bstn) {
        _root = bstn;
    }
}