import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        // 마라톤 선수가 완주한 여부를 체크하는 목록 생성 (기본값 0, 동명이인은 +1)
        Map<String, Integer> checking = new HashMap<>();
        Arrays.stream(participant).forEach(player -> checking.put(player, checking.getOrDefault(player, 0) + 1));
        
        // 완주자 체킹 (완주자의 값: -1)
        Arrays.stream(completion).forEach(completed -> checking.put(completed, checking.getOrDefault(completed, 0) - 1));
        
        // 완주하지 못한 사람 탐색
        String answer = checking.entrySet().stream()
                                .filter(entry -> entry.getValue() > 0)
                                .map(Map.Entry::getKey)
                                .findFirst()
                                .orElse("");
        
        return answer;
    }
}