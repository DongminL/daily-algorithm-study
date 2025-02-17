import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = players.clone();  // 경주가 끝난 후 최종 순위
        
        // 선수들의 순위 (0 ~ n-1)
        Map<String, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            rankMap.put(players[i], i);
        }
        
        // 순위 변동
        for (String calling : callings) {
            int rank = rankMap.get(calling);    // 현재 순위
            int frontRank = rank - 1;   // 현재 순위의 바로 앞 순위
            
            // 순위 서로 바꾸기
            String temp = answer[frontRank];
            answer[rank] = temp;
            answer[frontRank] = calling;
            
            // 순위 갱신
            rankMap.put(calling, frontRank);
            rankMap.put(temp, rank);
        }
        
        return answer;
    }
}