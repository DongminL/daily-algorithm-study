class Solution {
    public int[] solution(int n) {
        int max = n * (n + 1) / 2;  // n일 때, 나올 수 있는 최대값
        int[] answer = new int[max];    // 달팽이 채우기의 일차원 배열 결과값
        int[][] triangle = new int[n][n];   // 직각 삼각형 형태의 배열
        
        // 아래, 오른쪽, 왼쪽 대각선 방향
        int[] dirX = {0, 1, -1};
        int[] dirY = {1, 0, -1};
        int direction = 0; // 방향 (처음은 아래로)
        
        // 초기값
        int x = 0;  // x 좌표
        int y = 0;  // y 좌표
        int value = 1;  // 배열의 값
        triangle[y][x] = value++;
        
        // 세 방향 반복하기
        while (value <= max) {
            int nextX = x + dirX[direction];    // 다음 x 좌표
            int nextY = y + dirY[direction];    // 다음 y 좌표
            
            // triangle 배열 범위 내에서 값이 0인 곳만 변경
            if (nextX >= 0 && nextY >= 0 && 
                nextX < n && nextY < n && triangle[nextY][nextX] == 0) {
                
                triangle[nextY][nextX] = value++;
                x = nextX;
                y = nextY;
            } else {
                direction = (direction + 1) % 3;    // 세 방향을 반복
            }
        }
        
        // 2차원 배열 -> 1차원 배열
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[index++] = triangle[i][j];
            }
        }
        
        
        return answer;
    }
}