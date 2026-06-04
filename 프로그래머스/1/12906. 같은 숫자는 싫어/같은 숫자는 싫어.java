import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        // 배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들
        List<Integer> answer = new ArrayList<>();
        
        int preNum = -1;
        
        for (int num : arr) {
            if (preNum < 0) {
                answer.add(num);
                preNum = num;
                continue;
            }
            
            if (preNum != num) {
                answer.add(num);
                preNum = num;
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}