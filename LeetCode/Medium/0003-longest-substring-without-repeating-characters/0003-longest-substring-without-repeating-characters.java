import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }

        int start = 0;  // Substring의 시작 인덱스
        int end = 0; // Substring의 마지막 인덱스
        int max = 0;    // 가장 긴 Substring의 길이
        Set<Character> check = new HashSet<>(); // 글자의 중복 확인

        // 초기설정
        check.add(s.charAt(end++));
        max++;
        
        // two pointer search
        while(end < s.length()) {
            if(check.contains(s.charAt(end))) {
                check.remove(s.charAt(start++));
            } else {
                check.add(s.charAt(end++));
                max = Math.max(max, end - start);
            }
        }

        return max;
    }
}