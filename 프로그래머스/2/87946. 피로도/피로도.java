import java.util.*;

class Solution {
    
    int answer; // 탐험할 수 있는 최대 던전 수
    
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        boolean[] visited = new boolean[dungeons.length];  // 던전 방문 여부
        
        // 각 던전마다 탐색
        dfs(k, 0, dungeons, visited);
        
        return answer;
    }
    
    /* 던전에 갈 수 있는 경우 탐색 (DFS) */
    private void dfs(int k, int count, int[][] dungeons, boolean[] visited) {
        // 최대 던전 수 갱신
        answer = Math.max(answer, count);
        
        // 탐험할 수 있는 던전 탐색
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                
                // 다음 던전 탐색
                dfs(k - dungeons[i][1], count + 1, dungeons, visited);
                
                visited[i] = false;
            }
        }
    }
}