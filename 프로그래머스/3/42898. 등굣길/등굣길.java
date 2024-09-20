import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // 물에 잠긴 지역이 포함된 지도 (물에 잠긴 곳: -1)
        int[][] map = new int[n+1][m+1];
        Arrays.stream(puddles)
            .forEach(waterCoor -> map[waterCoor[1]][waterCoor[0]] = -1);
        
        // 각 좌표까지 갈 수 있는 경우의 수를 계산
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                // 초기값 지정 (집의 위치)
                if (x == 1 && y == 1) {
                    map[y][x] = 1;
                    continue;
                }
                
                if (map[y][x] != -1) {
                    // 현재 위치에서 왼쪽과 위쪽에 물에 잠긴 지역이 있을 수 있으므로
                    // -1(물에 잠긴 지역)을 0으로 변환하여 경우의 수 계산에 지장이 없도록 함
                    map[y][x] = Math.max(map[y][x-1], map[y][x]) + Math.max(map[y-1][x], map[y][x]);
                    map[y][x] %= 1000000007;
                }
            }
        }
        
        return map[n][m];   // 집에서 학교까지 최단경로의 수 
    }
}