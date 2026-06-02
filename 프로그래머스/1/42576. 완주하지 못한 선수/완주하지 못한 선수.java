import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        // 완주하지 못한 사람
        String answer = "";
        
        // 마라톤 선수가 완주한 여부를 체크
        Map<String, Integer> check = new HashMap<>();
        for (String player : participant) {
            check.put(player, check.getOrDefault(player, 0) + 1); // 동명이인은 + 1
        }
        
        // 완주자 체킹
        for (String completed : completion) {
            check.put(completed, check.get(completed) - 1);
        }
        
        // 완주하지 못한 사람 탐색
        for (Map.Entry<String, Integer> entry : check.entrySet()) {
            if (entry.getValue() > 0) {
                answer = entry.getKey();
                break;
            }
        }
        
        return answer;
    }
}