class Solution {
    public String solution(String s, int n) {
        String answer = ""; // s를 n만큼 민 암호문
        final int ALPHABET_COUNT = 26;  // 알파벳 개수
        
        // 시저 암호 만들기
        for (char c : s.toCharArray()) {
            // 공백
            if (c == ' ') {
                answer += c;
                continue;
            }
            
            // 알파벳을 n만큼 밀어내기
            if (c >= 'A' && c <= 'Z') {    // 대문자
                // 순환 구조
                answer += (char) ((c - 'A' + n) % ALPHABET_COUNT + 'A');
                
            } else {    // 소문자
                // 순환 구조
                answer += (char) ((c - 'a' + n) % ALPHABET_COUNT + 'a');
            }
        }
        
        return answer;
    }
}