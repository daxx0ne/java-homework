package HW08_2_Trie;

import java.util.ArrayList;

/*  트리 구조를 사용하여 문자열을 저장, 검색하는 클래스 */
public class Trie {
    private TrieNode _root; // 루트 노드
    private String _name; // 객체 이름

    public Trie(String name) {
        _root = new TrieNode();
        _name = name;
    }

    // 주어진 단어를 Trie에 삽입하는 메서드
    public void insert(String word) {
        TrieNode current = _root;
        for (char ch : word.toCharArray()) {
            if (!current.getChildren().containsKey(ch)) {
                current.getChildren().put(ch, new TrieNode(ch));
            }
            current = current.getChildren().get(ch);
        }
        current.setLeaf(true);
        current.setKeyWord(word);
    }

    // 주어진 단어가 Trie에 있는지 찾는 메서드
    public boolean search(String word) {
        TrieNode current = _root;
        for (char ch : word.toCharArray()) {
            if (!current.getChildren().containsKey(ch)) {
                return false;
            }
            current = current.getChildren().get(ch);
        }
        return current.isLeaf();
    }

    // Trie에 저장된 모든 단어를 반환하는 메서드
    public ArrayList<String> getKeywords() {
        ArrayList<String> keywords = new ArrayList<>();
        collectKeywords(_root, "", keywords);
        return keywords;
    }

    // Trie를 순회하면서 단어를 구성, 추가하는 재귀 메서드
    private void collectKeywords(TrieNode node, String currentWord, ArrayList<String> keywords) {
        if (node.isLeaf()) {
            keywords.add(currentWord);
        }

        for (char ch : node.getChildren().keySet()) {
            collectKeywords(node.getChildren().get(ch), currentWord + ch, keywords);
        }
    }

    // 주어진 키워드를 시작으로 하는 단어를 찾고 그 목록을 반환하는 메서드
    public ArrayList<String> getPredictiveWords(String keyword) {
        ArrayList<String> predictiveWords = new ArrayList<>();
        TrieNode current = _root;
        for (char ch : keyword.toCharArray()) {
            if (!current.getChildren().containsKey(ch)) {
                return predictiveWords;
            }
            current = current.getChildren().get(ch);
        }

        collectKeywords(current, keyword, predictiveWords);
        return predictiveWords;
    }

    // 객체 이름을 반환하는 메서드
    public String getName() {
        return _name;
    }
}
