import java.util.*;

class Solution {
    public int solution(String[] board) {
        Point start = null;  // 출발지점 
        Point end = null;    // 도착지점
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length(); x++) {
                if (board[y].charAt(x) == 'R') {
                    start = new Point(x, y, 0);
                }

                if (board[y].charAt(x) == 'G') {
                    end = new Point(x, y, 0);
                }

                if (start != null && end != null) {
                    break;
                }
            }
        }

        int answer = bfs(start, end, board);    // 리코쳇 로봇의 최소 움직임 횟수

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private int bfs(Point start, Point end, String[] board) {
        // 남, 서, 북, 동
        int[] dX = {0, -1, 0, 1};  // x좌표 변화
        int[] dY = {1, 0, -1, 0}; // y좌표 변화

        // 보드게임의 최대 크기
        int maxX = board[0].length();
        int maxY = board.length;

        int count = Integer.MAX_VALUE;  // 움직인 횟수
        boolean[][] visited = new boolean[maxY][maxX];  // 방문한 좌표 확인
        Deque<Point> queue = new ArrayDeque<>();

        // 시작 위치 설정
        queue.offerLast(start);
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Point now = queue.pollFirst();  // 현재 위치
            
            // 도착지점일 때
            if (now.isSameCoordinate(end)) {
                count = Math.min(count, now.count);
                continue;
            }

            // 네 방향으로 움직이기
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dX[i];
                int nextY = now.y + dY[i];
                int nextCount = now.count + 1;

                // 한 방향으로 벽을 만날 때까지 직진
                while (!isWall(nextX, nextY, maxX, maxY, board)) {
                    nextX += dX[i];
                    nextY += dY[i];
                }
                
                // 벽 이전으로 이동
                if (isWall(nextX, nextY, maxX, maxY, board)) {
                    nextX -= dX[i];
                    nextY -= dY[i];
                }
                
                if (!visited[nextY][nextX]) {
                    queue.offerLast(new Point(nextX, nextY, nextCount));
                    visited[nextY][nextX] = true;
                }
            }
        }

        return count;
    }

    // 못 지나가는지 확인
    private boolean isWall(int x, int y, int maxX, int maxY, String[] board) {
        // 게임판 외부일 때
        if (x < 0 || x >= maxX || y < 0 || y >= maxY) {
            return true;
        }
        // 장애물일 때
        return board[y].charAt(x) == 'D';
    }
}

// 지점
class Point {
    
    int x;
    int y;
    int count;  // 해당 위치까지 움직인 횟수

    public Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }

    public boolean isSameCoordinate(Point other) {
        return this.x == other.x && this.y == other.y;
    }
}