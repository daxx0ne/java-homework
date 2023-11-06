package HW08_2_Trie;

import java.util.TreeMap;

/*  Trie의 각 노드를 나태내는 클래스 */
public class TrieNode {
    private char ch;
    private String keyword;
    private TreeMap<Character, TrieNode> children = new TreeMap<>();
    private boolean isLeaf; // true = 단어의 끝일 때

    public TrieNode() {
    }

    public TrieNode(char ch) {
        this.ch = ch;
    }

    // 노드의 문자를 반환하는 메서드
    public char getCh() {
        return this.ch;
    }

    // 현재 노드의 자식 노드를 반환하는 메서드
    public TreeMap<Character, TrieNode> getChildren() {
        return children;
    }

    // 자식 노드를 설정하는 메서드
    public void setChildren(TreeMap<Character, TrieNode> children) {
        this.children = children;
    }

    // 현재 노드가 단어의 끝인지 아닌지를 반환하는 메서드
    public boolean isLeaf() {
        return isLeaf;
    }

    // 현재 노드가 단어의 끝을 나타내는 여부를 설정하는 메서드
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    // 노드에 연결된 키워드를 설정하는 메서드
    public void setKeyWord(String keyword) {
        this.keyword = keyword;
    }

    // 노드에 연결된 키워드를 반환하는 메서드
    public String getKeyWord() {
        return this.keyword;
    }
}
