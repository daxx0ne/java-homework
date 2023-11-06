package HW08_1_TreeMultiMap_ThesaurusDict;

import java.util.ArrayList;

/* 동의어 사전 목록을 나타내는 클래스 */
public class Thesaurus {
    private String keyword;
    private String type;
    private ArrayList<String> list_thesaurus = new ArrayList<>();

    // 키워드를 설정하는 메서드
    public void setKeyWord(String keyword) {
        this.keyword = keyword;
    }

    // 타입을 설정하는 메서드
    public void setType(String type) {
        this.type = type;
    }

    // 동의어 사전 목록에 추가하는 메서드
    public void addThesaurus(String word) {
        list_thesaurus.add(word);
    }

    // 동의어 목록을 문자열로 반환하는 메서드
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(keyword).append(" [").append(type).append("]: {");

        for (int i = 0; i < list_thesaurus.size(); i++) {
            sb.append(list_thesaurus.get(i));
            if (i < list_thesaurus.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
