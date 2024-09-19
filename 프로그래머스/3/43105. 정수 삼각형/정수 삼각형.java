import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0; // 거쳐간 숫자의 최댓값
        int height = triangle.length;   // 삼각형의 높이
        
        // 삼각형의 높이가 1일 때
        if (height == 1) {
            return triangle[0][0];
        }
        
        // 삼각형 높이가 2이상일 때
        // 부모 노드 중 가장 큰 값을 더해가면서 내려감
        for (int i = 1; i < height; i++) {
            for (int j = 0; j < i+1; j++) {
                
                if (j == 0) {   // 왼쪽 대각선으로 내려갈 때
                    triangle[i][j] += triangle[i-1][j];
                } else if (j == i) {    // 오른쪽 대각선으로 내려갈 때
                    triangle[i][j] += triangle[i-1][j-1];
                } else if (i > 0 && j < i) {
                    // 두 부모 노드 중에서 더 큰 값을 더함
                    triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);   
                }
                
                // 최대값 갱신
                answer = Math.max(answer, triangle[i][j]);
            }
        }
        
        return answer;
    }
}