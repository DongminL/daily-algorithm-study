import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        // 모든 원소에 대한 뒷 큰수들
        int[] answer = new int[numbers.length];
        
        // 뒤에 있는 큰수 후보들을 담는 스택
        Deque<Integer> stack = new ArrayDeque<>();
        
        // 뒷 큰수 찾기
        for (int i = numbers.length - 1; i > -1; i--) {
            while (!stack.isEmpty()) {
                // 뒷 큰 수 존재할 때
                if (stack.peek() > numbers[i]) {
                    answer[i] = stack.peek();
                    break;
                }
                
                // 뒷 큰 수가 없을 때, 스택에서 작은 수 빼기
                stack.pop();
            }
            
            // 뒷 큰 수가 없을 때
            if (stack.isEmpty()) {
                answer[i] = -1;   
            }
            
            stack.push(numbers[i]);
        }
        
        return answer;
    }
}