import java.util.*;

class Solution {
    public int solution(int[] nums) {
        // 폰켓몬의 수
        int answer = nums.length;   
        
        // 각 종류마다의 폰켓몬의 수 
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 1) + 1);
        }
        
        int typeCount = count.keySet().size();  // 폰켓몬 종류의 수
        
        // 가장 많은 종류의 폰켓몬을 선택할 때, 종류의 수
        if (typeCount <= answer / 2) {
            return typeCount;
        }
        
        return answer / 2;
    }
}