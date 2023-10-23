package HW07_3_Complete_Binary_Tree;

/* 작업을 나타내는 클래스 */
public class Task {
    protected int _priority; // 우선순위
    protected String _title; // 작업명

    // HeapPriQ_Task 클래스를 통해 우선순위 큐에 저장되고 관리됨
    public Task(int key, String value) {
        this._priority = key;
        this._title = value;
    }

    // 작업의 우선순위를 반환하는 메서드
    public int getPriority() {
        return this._priority;
    }

    // 작업 내용을 문자열로 반환하는 메서드
    public String toString() {
        return String.format("Ev(%d, %s)", this._priority, this._title);
    }
}
