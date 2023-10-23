package HW07_3_Complete_Binary_Tree;

/* Object를 지정된 크기만큼의 배열로 저장할 수 있도록 하는 클래스 */
public class GenericArray<E> { // 제너릭 타입 E를 사용하여 객체를 저장하는 배열을 관리
    protected Object[] genArray; // 객체 배열
    protected int capacity; // 배열의 용량
    protected int size; // 배열에 저장된 객체 수

    public GenericArray(int capa) { // 주어진 용량을 가진 배열을 초기화
        this.genArray = new Object[capa];
        this.capacity = capa;
        this.size = 0;
    }
}