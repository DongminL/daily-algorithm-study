import java.util.*;

class Solution {
    
    // 지표별 유형 (알파벳순)
    char[][] types = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
    
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> summary = new HashMap<>();  // 타입별 검사 점수
        for (char[] type : types) {
            summary.put(type[0], 0);
            summary.put(type[1], 0);
        }
        
        // 각 유형별 점수 계산
        for (int i = 0; i < survey.length; i++) {
            int score= toScore(choices[i]);
            char left = survey[i].charAt(0);
            char right = survey[i].charAt(1);
            
            if (score < 0) {
                summary.put(left, summary.get(left) + Math.abs(score));
            } else {
                summary.put(right, summary.get(right) + Math.abs(score));
            }
        }
        
        String answer = ""; // 최종 성격 유형
        for (char[] type : types) {
            answer += (summary.get(type[0]) < summary.get(type[1])) ? type[1] : type[0];
        }
        
        return answer;
    }
    
    // 각 선택지를 점수로 변경
    private int toScore(int choice) {
        if (choice < 4) {
            return choice - 4;
        }
        return choice % 4;
    }
}