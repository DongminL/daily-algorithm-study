import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Deque<Integer> stack = new ArrayDeque<>();  // 뒷 큰 수 후보들을 담는 스택
        
        // 뒷 큰 수 찾기
        for (int i = numbers.length - 1; i > -1; i--) {
            while (!stack.isEmpty()) {
                if (stack.peek() > numbers[i]) {    // 뒷 큰 수 존재할 때
                    answer[i] = stack.peek();
                    break;
                } else {    // 뒷 큰 수가 없을 때, 스택에서 작은 수 빼기
                    stack.pollFirst();
                }
            }
            
            // 뒷 큰 수가 없을 때
            if (stack.isEmpty()) {
                answer[i] = -1;   
            }
            
            stack.offerFirst(numbers[i]);
        }
        
        return answer;
    }
}