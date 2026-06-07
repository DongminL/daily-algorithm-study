import java.util.*;

class Solution {
    public int solution(int[] nums) {
        // 폰켓몬의 종류 번호의 개수
        int answer = nums.length;   
        
        // 각 종류마다의 개수 
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 1) + 1);
        }
        
        // 폰켓몬 종류 가지수
        int typeCount = count.keySet().size();
        
        // 가장 다양한 종류의 폰켓몬을 선택
        answer = typeCount <= answer / 2 ? 
            typeCount : answer / 2;
        
        return answer;
    }
}