package HW06_2_Thesaurus_Dictionary;

import java.util.ArrayList;

/* keyword 에 대한 유의어 목록을 나타내는 클래스 */
class Thesaurus {
    private String keyword; // 키워드
    private String type; // 품사
    private ArrayList<String> list_thesaurus = new ArrayList<>(); // 유의어 목록

    public void setKeyWord(String keyword) {
        this.keyword = keyword;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addThesaurus(String word) {
        list_thesaurus.add(word);
    }

    // 키워드, 품사, 유의어 목록을 문자열로 반환하는 메서드
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(keyword).append(" [").append(type).append("] : {");

        for (int i = 0; i < list_thesaurus.size(); i++) { // 유의어 목록의 각 요소를 순회하며 유의어를 추가함
            result.append(list_thesaurus.get(i));
            if (i < list_thesaurus.size() - 1) {
                result.append(", ");
            }
        }

        result.append(", }");

        return result.toString(); // result에 저장된 유의어들을 모두 문자열로 반환함
    }
}
