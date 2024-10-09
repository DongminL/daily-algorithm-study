import java.util.*;

class Solution {
    public String solution(String s) {
        // s를 내림차순 정렬한 문자열
        String answer = "";
        
        // String -> char[]
        char[] charArray = s.toCharArray();
        
        // 오름차순 정렬
        Arrays.sort(charArray);
        
        // 내림차순으로, char[] -> String
        for (int i = charArray.length - 1; i >= 0; i--) {
            answer += charArray[i];
        }
        
         // s를 내림차순 정렬한 문자열
        return answer;
    }
}