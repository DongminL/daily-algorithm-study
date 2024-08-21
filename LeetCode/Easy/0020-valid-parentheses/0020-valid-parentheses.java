import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            // 여는 괄호만 스택에 추가
            if (c == '(' || c == '{' || c == '[') {
                stack.offerFirst(c);
            } else {    // 닫는 괄호일 때
                if (stack.isEmpty()) {
                    return false;
                }

                char open = stack.pollFirst();  // 여는 괄호
                
                // 서로 다른 괄호일 때
                if (c == ')' && open != '(') {
                    return false;
                } else if (c == '}' && open != '{') {
                    return false;
                } else if (c == ']' && open != '[') {
                    return false;
                }
            }
        }

        return stack.isEmpty(); // 반복문 후 스택이 비어있어야 함
    }
}