import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(String s) {
        int answer = 0;    //올바른 괄호 문자열이 되게 하는 x의 개수
        
        // String -> StringBuilder
        StringBuilder rotation = new StringBuilder(s);
        
        // s를 회전시키며 올바른 괄호인지 확인
        for (int i = 0; i < s.length(); i++) {
            // 올바른 괄호인지 확인
            if (check(rotation.toString())) {
                answer++;
            }
            
            // 괄호 회전
            char temp = rotation.charAt(0);
            rotation.deleteCharAt(0).append(temp);
        }
        
        return answer;
    }
    
    /* 해당 문자열이 올바른 괄호인지 확인 */
    private boolean check(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        
        // 괄호쌍 확인
        for (char c : s.toCharArray()) {
            // 열린 괄호일 때
            if (c == '(') {
                stack.offerFirst(')');
                continue;
            }
            if (c == '{') {
                stack.offerFirst('}');
                continue;
            }
            if (c == '[') {
                stack.offerFirst(']');
                continue;
            }
            
            // 닫힌 괄호가 나왔는데 스택이 비어 있을 경우
            if (stack.isEmpty()) {
                return false;
            }
            
            // 열린 괄호와 닫힌 괄호가 서로 다를 경우
            if (c != stack.peek()) {
                return false;
            }
            
            // 같은 쌍인 경우
            stack.pollFirst();
        }
        
        // 스택에 괄호가 남아 있으면 쌍이 안 맞는 것
        if (!stack.isEmpty()) {
            return false;
        }
        
        return true;
    }
}