package HW06_1_TreeMap;

/* 전화번호를 나타내는 클래스 */
class TelNum {
    private int nationCode; // 국가코드
    private int regionNumber; // 지역번호
    private int switchNumber; // 교환기번호
    private int lineNumber; // 선 번호

    // 객체 초기화
    public TelNum(int nationCode, int regionNumber, int switchNumber, int lineNumber) {
        this.nationCode = nationCode;
        this.regionNumber = regionNumber;
        this.switchNumber = switchNumber;
        this.lineNumber = lineNumber;
    }

    // 출력 포멧
    @Override
    public String toString() {
        // 3, 3, 4, 4 자리로 표시하고 빈자리는 0으로 채우기, 번호 사이는 -로 연결하기
        return String.format("%03d-%03d-%04d-%04d", nationCode, regionNumber, switchNumber, lineNumber);
    }
}