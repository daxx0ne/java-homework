/*
 * Homework 8.2 Trie 기반 예측문자열 검색기 구현
 * 이름: 박다원
 * 학번: 21912154
 */

package HW08_2_Trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App_Trie {
    public static void main(String[] args) throws FileNotFoundException {
        Trie trie_str = new Trie("Trie_String");
        final String fname = "/Users/daone/IdeaProjects/java-homework/src/HW08_2_Trie/keywords.txt";
        Scanner fin = new Scanner(new File(fname));
        String w;
        while (fin.hasNext()) {
            w = fin.next();
            trie_str.insert(w);
        }
        String[] test_words = {"abcd", "ab", "abc", "k", "x", "z"};
        for (String kw : test_words) {
            if (trie_str.search(kw)) {
                System.out.printf("%5s : found in trie\n", kw);
            } else {
                System.out.printf("%5s : not found in trie\n", kw);
            }
        }
        ArrayList<String> keywords;
        keywords = trie_str.getKeywords();
        System.out.printf("Total keywords of %s : ", trie_str.getName());
        for (String word : keywords) {
            System.out.printf("%s ", word);
        }
        System.out.print("\n");
        String test_keyword = "ab";
        keywords = trie_str.getPredictiveWords(test_keyword);
        System.out.printf("Predictive words of %s : ", test_keyword);
        for (String word : keywords) {
            System.out.printf("%s ", word);
        }
        System.out.print("\n");
    }
}
