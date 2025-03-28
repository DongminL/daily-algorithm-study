import java.util.*;

class Solution {
    public int[] solution(String[] names, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];   // 각 사진에 대한 그리움 점수
        
        // 각 사람에 대한 그리움 점수 매핑
        Map<String, Integer> scores = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            scores.put(names[i], yearning[i]);
        }
        
        for (int i = 0; i < photo.length; i++) {
            int photoScore = 0;  // 특정 사진에 대한 그리움 점수
            
            // 해당 사진에 대한 그리움 점수 구하기
            for (String name : photo[i]) {
                Integer score = scores.get(name);
                if (score != null) {
                    photoScore += score;
                }
            }
            
            answer[i] = photoScore;
        }
        
        return answer;
    }
}