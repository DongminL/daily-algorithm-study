import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0; // 정확한 순위를 매길 수 있는 선수의 수
        
        // results -> 인접 행렬로 변환
        int[][] matrix = new int[n+1][n+1]; // 선수간의 승패 여부
        for (int[] r : results) {
            matrix[r[0]][r[1]] = 1; // A가 B를 이길 때
            matrix[r[1]][r[0]] = -1; // B가 A에게 질 때
        }
        
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    // a -> k -> b 순으로 순위 형성 (a가 k, b를 이김)
                    if (matrix[a][k] == 1 && matrix[k][b] == 1) {
                        matrix[a][b] = 1;
                        matrix[b][a] = -1;
                    }
                }
            }
        }
        
        // 순위를 매길 수 있는 선수의 여부
        answer = n;
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                // 선수간의 승패를 알 수 없는 경우
                if (a != b && matrix[a][b] == 0) {
                    answer--;
                    break;
                }
            }
        }
        
        return answer;
    }
}