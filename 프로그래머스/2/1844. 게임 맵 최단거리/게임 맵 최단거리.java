import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int[][] direction = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};  // 동, 남, 서, 북
        int[][] visited = new int[maps.length][maps[0].length]; // 해당 칸에 방문한 최소 횟수
        
        // 도착지까지 지나온 칸의 최소 개수
        int answer = bfs(maps, direction, visited);
        
        // 도착지가 막혀있는 경우
        if (answer == 0) {
            return -1;
        }
        
        return answer;
    }
    
    /* 도착지까지 지나간 칸의 최소 개수 (BFS) */
    private int bfs(int[][] maps, int[][] direction, int[][] visited) {
        Deque<Location> queue = new ArrayDeque<>();
        
        // (0, 0)부터 시작
        queue.offerLast(new Location(0, 0)); 
        visited[0][0] = 1;
        
        // 경로 탐색
        while (!queue.isEmpty()) {
            Location cur = queue.pollFirst();   // 현재 위치
            
            // 네 방향 확인
            for (int i = 0; i < direction.length; i++) {
                int nextX = cur.x + direction[i][0];    // 다음 X 좌표
                int nextY = cur.y + direction[i][1];    // 다음 Y 좌표
                
                // 유효한 공간이 아닌 경우
                if (!isSpace(nextX, nextY, maps.length, maps[0].length)) {
                    continue;
                }
                
                // 다음 좌표로 이동
                if (visited[nextX][nextY] == 0 && maps[nextX][nextY] == 1) {
                    queue.offerLast(new Location(nextX, nextY));
                    visited[nextX][nextY] = visited[cur.x][cur.y] + 1;
                }
            }
        }
        
        // 도착지까지 지나온 칸의 최소 개수
        return visited[maps.length - 1][maps[0].length - 1];
    }
    
    // 유효한 공간인지 확인
    private boolean isSpace(int x, int y, int maxX, int maxY) {
        return x > -1 && x < maxX && y > -1 && y < maxY;
    }
}

/* 위치 정보에 대한 클래스 */
class Location {
    
    int x;
    int y;
    
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}