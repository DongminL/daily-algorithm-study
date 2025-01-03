class Solution {
    public String solution(String s) {
        StringBuilder jadenCase = new StringBuilder();
        boolean isWordFirst = true;    // 단어의 첫 글자인지 구분
        
        // 단어로 분리
        String[] strArray = s.trim().split(" ");
        
        // JadenCase로 변환
        for (int i = 0; i < s.length(); i++) {
            // 공백일 때
            if (s.charAt(i) == ' ') {
                jadenCase.append(" ");
                
                // 다음에 글자가 나오면 무조건 단어의 첫 글자
                isWordFirst = true;
                continue;
            }
            
            // 공백이 아닐 때
            if (isWordFirst) {
                jadenCase.append(s.substring(i, i+1).toUpperCase());
                
                isWordFirst = false;
            } else {
                jadenCase.append(s.substring(i, i+1).toLowerCase());
            }
        }
        
        return jadenCase.toString();
    }
}