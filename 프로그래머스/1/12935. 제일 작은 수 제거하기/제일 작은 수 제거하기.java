import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        List<Integer> answer = new ArrayList<>();
        
        int min = Arrays.stream(arr).min().getAsInt();
        
        for (int v : arr) {
            if (v == min) {
                continue;
            }
            answer.add(v);
        }
        
        if (answer.isEmpty()) {
            return new int[] { -1 };
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}