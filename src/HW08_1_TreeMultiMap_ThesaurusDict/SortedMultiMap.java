package HW08_1_TreeMultiMap_ThesaurusDict;

import java.util.*;

/* 키와 값으로 구성된 데이터를 저장하고 키를 기준으로 정렬하는 클래스 */
class SortedMultiMap<K, V> {
    private Map<K, Collection<V>> sortedMap = new TreeMap<>();

    // 데이터 추가
    public void put(K key, V value) {
        sortedMap.computeIfAbsent(key, k -> new ArrayList<V>()); // computeIfAbsent: 키가 존재하지 않으면 새로운 키를 생성하고 값을 저장
        if (!sortedMap.get(key).contains(value)) { // 키 값이 없는 경우에 값을 추가
            sortedMap.get(key).add(value);
        }
    }

    // 키 값 반환
    public Collection<V> get(Object key) {
        return sortedMap.get(key);
    }

    // 모든 키의 집합을 정렬하여 반환
    public Set<K> keySet() {
        return sortedMap.keySet();
    }

    // 엔트리 집합 반환
    public Set<Map.Entry<K, Collection<V>>> entrySet() {
        return sortedMap.entrySet(); }
}
