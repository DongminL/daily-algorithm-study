import java.io.*;
import java.util.*;

class Solution {

    String solution(String str, String boomStr) {
        // 모든 폭발이 끝난 후 남은 문자열
        String answer = "";

        // 문자열을 검증하기 위한 스택
        Deque<Character> stack = new ArrayDeque<>(str.length());

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            // 폭발 문자열인지 검증
            if (stack.peek() == boomStr.charAt(boomStr.length() - 1) && stack.size() >= boomStr.length()) {
                // 폭발 문자열인지 의심되는 문자열
                StringBuilder temp = new StringBuilder(boomStr.length());

                // 폭발 문자열 길이만큼 스택에서 제거하여 의심 문자열 만듦
                for (int j = 0; j < boomStr.length(); j++) {
                    temp.append(stack.pop());
                }
                temp.reverse();

                // 의심한 문자열이 폭발 문자열이 아닐 경우, 다시 스택에 넣기
                if (!temp.toString().equals(boomStr)) {
                    for (int j = 0; j < temp.length(); j++) {
                        stack.push(temp.charAt(j));
                    }
                }
            }
        }

        answer = makeRestString(stack);

        return answer;
    }

    // 폭발 후 남아있는 문자로 문자열 만들기
    private String makeRestString(Deque<Character> stack) {
        // 남아있는 문자가 없는 경우
        if (stack.isEmpty()) {
            return "FRULA";
        }

        StringBuilder result = new StringBuilder(stack.size());

        // 폭발 후 남은 문자열
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        result.reverse();

        return result.toString();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String boomStr = br.readLine();

        System.out.println(new Solution().solution(str, boomStr));
    }
}