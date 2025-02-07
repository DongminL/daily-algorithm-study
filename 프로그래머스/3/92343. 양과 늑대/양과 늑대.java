class Solution {
    
    int[] infoArr;
    int[][] edgesArr;
    int answer = 0; // 양의 최대 수
    
    public int solution(int[] info, int[][] edges) {
        // 전역 변수로 만들기
        infoArr = info;
        edgesArr = edges;
        
        dfs(0, 0, 0, new boolean[info.length]);
        
        return answer;
    }
    
    // 트리 DFS 탐색
    private void dfs(int current, int sheepCount, int wolfCount, boolean[] visited) {
        visited[current] = true;
        
        // 현재 위치가 양일 때
        if (infoArr[current] == 0) {
            sheepCount++;
            answer = Math.max(answer, sheepCount);  // 최대값 갱신
        } else {
            // 현재 위치가 늑대일 때
            wolfCount++;
        }
        
        // 늑대가 모든 양을 잡아 먹을 때
        if (sheepCount <= wolfCount) {
            return; // 탐색 종료
        }
        
        // 방문하지 않은 자식 노드 탐색하기
        for (int[] edge : edgesArr) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                dfs(edge[1], sheepCount, wolfCount, visited.clone());
            }
        }
    }
}