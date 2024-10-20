class Solution {
    public String solution(String s, int n) {
        String answer = ""; // s를 n만큼 민 암호문
        
        // 시저 암호 만들기
        for (char c : s.toCharArray()) {
            // 공백
            if (c == ' ') {
                answer += c;
                continue;
            }
            
            // n만큼 민 글자
            int nextChar = c + n;
            
            if (c >= 'A' && c <= 'Z') {    // 알파벳 대문자
                
                // 대문자 순환 구조
                if (nextChar > 'Z') {
                    answer += (char) (nextChar % ('Z' + 1) + 'A');
                } else {
                    answer += (char) nextChar;
                }
                
            } else {    // 알파벳 소문자
                
                // 소문자 순환 구조
                if (nextChar > 'z') {
                    answer += (char) (nextChar % ('z' + 1) + 'a');
                } else {
                    answer += (char) nextChar;
                }
            }
        }
        
        return answer;
    }
}