package HW06_2_Thesaurus_Dictionary;

/* 유의어 목록 중 키워드와, 해당 품사를 나타내는 클래스 */
class Word_Type {
    private String word;
    private String type;

    // Word_Type 객체는 HashMap의 키로 사용됨
    public Word_Type(String word, String type) {
        this.word = word;
        this.type = type;
    }

    // 키워드와 품사를 문자열로 반환하는 메서드
    @Override
    public String toString() {
        return word + "_" + type;
    }
}