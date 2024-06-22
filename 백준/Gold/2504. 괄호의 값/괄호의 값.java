import java.io.*;
import java.util.*;

public class Main {
    
    static Deque<Character> stack = new ArrayDeque<>();
    static int result = 0;
    static int value = 1;

    static void calParentheses(String parentheses, int length) {
        char p;

        for (int i = 0; i < length; i++) {
            p = parentheses.charAt(i);

            if (p == '(') {
                stack.push('(');
                value *= 2;
            } else if (p == '[') {
                stack.push('[');
                value *= 3;
            } else if (p == ')' && !stack.isEmpty() && stack.peek() == '(') {
                if (parentheses.charAt(i-1) == '(') {
                    result += value;
                }
                
                stack.pop();
                value /= 2;
            } else if (p == ']' && !stack.isEmpty() && stack.peek() == '[') {
                if (parentheses.charAt(i-1) == '[') {
                    result += value;
                }
                
                stack.pop();
                value /= 3;
            } else {
                result = 0;
                return;
            }
        }
        
        if (!stack.isEmpty()) {
            result = 0;
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String parenthes = br.readLine();

        calParentheses(parenthes, parenthes.length());

        System.out.println(result);
    }
}