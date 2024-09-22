import java.util.*;

class Solution {
    
    ArrayList<Integer>[] list;  // 인접 리스트
    boolean[] visited;  // 각 컴퓨터에 방문한 여부
    
    public int solution(int n, int[][] computers) {
        // 초기화
        visited = new boolean[n];
        list = new ArrayList[n];
        Arrays.setAll(list, ArrayList::new);
        
        // 네트워크를 인접 리스트로 표현
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    list[i].add(j);
                    list[j].add(i);
                }
            }
        }
        
        int answer = 0; // 네트워크의 개수
        for (int i = 0; i < n; i++) {
            // 서로 분리된 네트워크만 탐색
            if (!visited[i]) {
                dfs(i, 0, n);
                answer++;
            }
        }
        
        return answer;
    }
    
    /* 네트워크의 개수 탐색 (DFS) */
    private void dfs(int start, int depth, int n) {
        if (depth > n) {
            return;
        }
        
        for (int next : list[start]) {
            if (!visited[next]) {
                visited[next] =true;
                dfs(next, depth+1, n);
            }
        }
    }
}