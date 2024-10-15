import java.util.*;

class Solution {
    public String solution(String my_string) {
        String answer = ""; // my_string에서 중복된 문자를 제거하고 하나의 문자만 남긴 문자열
        
        // 중복 제거
        Set<Character> set = new HashSet<>();
        for (char c : my_string.toCharArray()) {
            if (!set.contains(c)) {
                set.add(c);
                answer += c;
            }
        }
        
        return answer;
    }
}