import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        // 완주하지 못한 사람
        String answer = "";
        
        // 마라톤 선수가 완주한 여부를 체크하는 목록 (기본값 0, 동명이인은 +1)
        Map<String, Integer> checking = new HashMap<>();
        for (String player : participant) {
            checking.put(player, checking.getOrDefault(player, 0) + 1);
        }
        
        // 완주자 체킹 (완주자의 값 -1)
        for (String completed : completion) {
            checking.put(completed, checking.getOrDefault(completed, 0) - 1);
        }
        
        // 완주하지 못한 사람 탐색
        answer = checking.entrySet().stream()
                                .filter(entry -> entry.getValue() > 0)
                                .map(Map.Entry::getKey)
                                .findFirst()
                                .orElse("");
        
        return answer;
    }
}