import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Deque<Character> stack = new ArrayDeque<>();
        
        // 괄호쌍 확인
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.offerFirst(')');
                continue;
            } 
            
            // 닫힌 괄호일 때
            if (stack.isEmpty()) {
                return false;
            }
            stack.pollFirst();
        }
        
        // 괄호가 남을 때
        if (!stack.isEmpty()) {
            return false;
        }

        return answer;
    }
}