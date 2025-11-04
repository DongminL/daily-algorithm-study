import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        // 배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들
        List<Integer> answer = new ArrayList<>();
        
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(arr[0]);
        
        // 연속적으로 나타나는 숫자들의 중복 제거
        for (int i = 1; i < arr.length; i++) {
            if (!stack.isEmpty() && stack.peek() != arr[i]) {
                answer.add(stack.pop());
                stack.push(arr[i]);
            }
        }
        answer.add(stack.pop());  // 마지막 숫자 추가

        return answer.stream().mapToInt(i -> i).toArray();
    }
}