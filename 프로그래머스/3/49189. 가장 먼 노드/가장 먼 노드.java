import java.util.*;

class Solution {
    
    ArrayList<Integer>[] list;  // 인접 리스트
    boolean[] visited;  // 각 노드에 방문한 여부
    
    public int solution(int n, int[][] edge) {
        // 초기화
        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
            
        // edge에 대한 정보를 인접리스트로 변경
        for(int[] e : edge) {
            // 양방향 엣지
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }
        
        // 가장 멀리 떨어진 노드의 개수 찾기
        int answer = 0; // 1번 노드에서 가장 멀리 떨어진 노드의 개수
        int max = 0;    // 1번 노드에서 최대로 이동한 횟수
        int[] moveCount = bfs(1, n);
        for (int i = 2; i <= n; i++) {
            if (max < moveCount[i]) {
                answer = 1;
                max = moveCount[i];
            } else if (max == moveCount[i]) {
                answer++;
            }
        }
    
        return answer;
    }
    
    /* start -> end까지의 이동한 수 세기 (BFS) */
    private int[] bfs(int start, int n) {
        int[] moveCount = new int[n+1];    // 1번에서 출발하여 각 노드까지 움직인 횟수
        Deque<Integer> queue = new ArrayDeque<>();
        visited = new boolean[n+1];
        
        queue.offerLast(1);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();    // 현재 노드
            
            // 다음 노드 방문
            for (int next : list[cur]) {
                if (!visited[next]) {   // 방문하지 않은 노드만
                    queue.offerLast(next);
                    moveCount[next] = moveCount[cur] + 1;   // 움직인 횟수 갱신
                    visited[next] = true;
                }
            }
            
        }
        
        return moveCount;
    }
}