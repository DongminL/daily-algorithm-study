import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        // 원하는 제품을 모두 할인 받을 수 있는 회원등록 날짜의 총 일수
        int answer = 0;
        
        // wand, number -> map으로 합침
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
        }
        
        // 10일씩 탐색
        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> clonedMap = new HashMap<>(map);
            
            // 할인 물품 사기
            for (int j = i; j < i + 10; j++) {
                String discountItem = discount[j];  // 할인 물품
                
                if (clonedMap.containsKey(discountItem)) {
                    clonedMap.put(discountItem, clonedMap.get(discountItem) - 1);
                    
                    // 원하는 만큼 다 샀을 때
                    if (clonedMap.get(discountItem) == 0) {
                        clonedMap.remove(discountItem);
                    }
                }
            }
            
            // 할인으로 10개 물품을 다 샀을 때
            if (clonedMap.isEmpty()) {
                answer++;
            }
        }
        
        return answer;
    }
}