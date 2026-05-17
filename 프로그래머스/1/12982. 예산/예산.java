import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        
        Arrays.sort(d);
        
        for (int amount : d) {
            if (amount <= budget) {
                budget -= amount;
                answer++;
            }
        }
        
        return answer;
    }
}