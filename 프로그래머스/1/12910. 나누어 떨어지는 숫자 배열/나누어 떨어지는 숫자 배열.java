import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        List<Integer> answer = new ArrayList<>();
        
        for (int num : arr) {
            if (num % divisor == 0) {
                answer.add(num);
            }
        }
        
        if (answer.isEmpty()) {
            return new int[] { -1 };
        }
        
        return answer.stream()
            .sorted()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}