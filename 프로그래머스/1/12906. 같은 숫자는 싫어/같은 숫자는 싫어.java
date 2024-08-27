import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        List<Integer> answer = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        
        // 연속적으로 나타나는 숫자만 answer에 추가
        stack.offerFirst(arr[0]);
        Arrays.stream(arr)
            .skip(1)
            .filter(num -> stack.peek() != num)
            .forEach(num -> {
                answer.add(stack.pollFirst());
                stack.offerFirst(num);
            });
        answer.add(stack.pollFirst());  // 마지막 연속값 추가

        return answer.stream().mapToInt(i -> i).toArray();
    }
}