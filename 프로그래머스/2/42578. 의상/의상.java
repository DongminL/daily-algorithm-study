import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        // 종류별 옷의 개수 구하기
        Map<String, Integer> typeCount = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            typeCount.put(clothes[i][1], typeCount.getOrDefault(clothes[i][1], 0) + 1);
        }
        
        // 전체 경우의 수 구하기
        for (String key : typeCount.keySet()) {
            answer *= (typeCount.get(key) + 1); // 안 입는 경우 추가
        }
        
        return answer - 1;  // 다 안 입는 경우 빼기
    }
}