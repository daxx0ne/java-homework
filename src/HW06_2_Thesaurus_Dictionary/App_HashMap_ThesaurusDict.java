/*
 * Homework 6.2 Thesaurus Dictionary 구현
 * 이름: 박다원
 * 학번: 21912154
 */

package HW06_2_Thesaurus_Dictionary;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class App_HashMap_ThesaurusDict {
    public static void main(String[] args) throws IOException {
        HashMap<Word_Type, Thesaurus> thesaurusDict = new HashMap<Word_Type, Thesaurus>(); // HashMap 을 사용한 유의어 사전
        final String fname = "/Users/daone/IdeaProjects/java-homework/src/HW06_2_Thesaurus_Dictionary/sample_thesaurus_dict.txt";
        Scanner fin = new Scanner(new File(fname));
        String keyword, word, type;
        while (fin.hasNext()) {
            String str_line = fin.nextLine();
            System.out.printf("ThesaurusDict:: processing %s\n", str_line);
            StringTokenizer strTokenizer = new StringTokenizer(str_line, " ");
            Thesaurus thesaurus = new Thesaurus();
            keyword = strTokenizer.nextToken();
            thesaurus.setKeyWord(keyword);
            type = strTokenizer.nextToken();
            thesaurus.setType(type);
            while (strTokenizer.hasMoreTokens()) { // 유의어 추가
                word = strTokenizer.nextToken();
                thesaurus.addThesaurus(word);
            }
            thesaurusDict.put(new Word_Type(keyword, type), thesaurus);
        }
        fin.close();
        Set<Word_Type> keys = thesaurusDict.keySet();
        System.out.printf("keys = %s\n", keys);
        for (Word_Type keyWordType : keys) {
            Thesaurus value = thesaurusDict.get(keyWordType);
            System.out.println("key (" + keyWordType + ") : " + value + ")");
        }
    }
}