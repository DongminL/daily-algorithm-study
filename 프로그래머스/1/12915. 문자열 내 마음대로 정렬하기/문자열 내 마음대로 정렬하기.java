import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        // 각 문자열의 인덱스 n번째 글자를 기준으로 오름차순 정렬
        Arrays.sort(strings, (s1, s2) -> {
            // 같은 문자열이 여럿 일 경우, 사전순으로 앞선 문자열이 앞쪽에 위치
            if (s1.charAt(n) == s2.charAt(n)) {
                return s1.compareTo(s2);
            }
            
            return Integer.compare(s1.charAt(n), s2.charAt(n));
        });
        
        return strings;
    }
}