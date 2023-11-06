package HW08_1_TreeMultiMap_ThesaurusDict;

import java.util.*;

/* 키와 값으로 구성된 데이터를 저장하고 키를 기준으로 정렬하는 클래스 */
class SortedMultiMap<K, V> {
    private Map<K, Collection<V>> sortedMap = new TreeMap<>();

    // 데이터 추가
    public void put(K key, V value) {
        if (!sortedMap.containsKey(key)) { // 키가 존재하지 않으면 새로운 키 생성 및 값 추가
            sortedMap.put(key, new ArrayList<>());
        }
        sortedMap.get(key).add(value);
    }

    // 키 값 반환
    public Collection<V> get(Object key) {
        return sortedMap.get(key);
    }

    // 키의 집합을 정렬하여 반환
    public Set<K> keySet() {
        return sortedMap.keySet();
    }
}
