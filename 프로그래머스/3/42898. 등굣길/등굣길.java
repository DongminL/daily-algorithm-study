import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // 물에 잠긴 지역이 포함된 지도 (물에 잠긴 곳: -1)
        int[][] map = new int[n + 1][m + 1];
        for (int[] puddle : puddles) {
            map[puddle[1]][puddle[0]] = -1;
        }
        
        // 집의 위치 설정
        map[1][1] = 1;
        
        // 각 좌표까지 갈 수 있는 경우의 수를 계산
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                // 집과 물에 잠긴 곳은 패스
                if ((y == 1 && x == 1) || map[y][x] == -1) {
                    continue;
                }
                
                // 현재 위치에서 왼쪽과 위쪽에 물에 잠긴 지역이 있을 수 있으므로
                // -1(물에 잠긴 지역)을 0으로 변환하여 경우의 수 계산에 지장이 없도록 함
                int west = Math.max(map[y][x - 1], map[y][x]);
                int north = Math.max(map[y - 1][x], map[y][x]);
                map[y][x] = (west + north) % 1_000_000_007;
            }
        }
        
        return map[n][m];   // 집에서 학교까지 최단경로의 수 
    }
}