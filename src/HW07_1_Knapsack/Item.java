package HW07_1_Knapsack;

/* 아이템의 이름, 가치, 무게 정보를 저장하는 클래스 */
public class Item {
    String name;
    int value;
    int weight;

    public Item(String nm, int val, int wt) {
        this.name = nm;
        this.value = val;
        this.weight = wt;
    }

    // 아이템 객체를 문자열로 반환
    public String toString() {
        String str;
        str = String.format("(%s, %d, %d)", this.name, this.value, this.weight);
        return str;
    }
}