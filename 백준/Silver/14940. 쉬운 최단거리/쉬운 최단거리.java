import java.io.*;
import java.util.*;

class Solution {

    private int n;  // 지도의 세로 크기
    private int m;  // 지도의 가로 크기
    private int[][] map;    // 지도 (0: 갈 수 없는 땅, 1: 갈 수 있는 땅, 2: 목표지점)

    public Solution(int n, int m, int[][] map) {
        this.n = n;
        this.m = m;
        this.map = map.clone();
    }

    int[][] solution() {
        // 각 지점부터 목표 지점까지의 거리
        int[][] answer = new int[n][m];

        // 탐색을 하며 방문한 곳 체크
        boolean[][] visited = new boolean[n][m];

        // 목표지점의 위치
        Point target = getTarget();

        bfs(target, answer, visited);

        // 도달할 수 없는 위치는 -1로 변경
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    answer[i][j] = -1;
                }
            }
        }

        return answer;
    }

    private void bfs(Point target, int[][] distance, boolean[][] visited) {
        // 북, 동, 남, 서 방향
        int[] dX = { 0, 1, 0, -1 };
        int[] dY = { -1, 0, 1, 0 };

        Queue<Point> queue = new ArrayDeque<>();

        // 목표지점부터 시작
        queue.offer(target);
        visited[target.y][target.x] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // 현재 지점으로부터 4방위 탐색
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dX[i];
                int nextY = current.y + dY[i];

                if (movable(nextX, nextY) && !visited[nextY][nextX]) {
                    // 거리 누적
                    distance[nextY][nextX] = distance[current.y][current.x] + 1;

                    // 탐색 순서 기록
                    queue.offer(new Point(nextX, nextY));
                    visited[nextY][nextX] = true;
                }
            }
        }
    }

    private boolean movable(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n
            && map[y][x] == 1;
    }

    private Point getTarget() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    return new Point(j, i);
                }
            }
        }
        return new Point(0, 0);
    }

    private static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = toIntArray(br.readLine());
        int[][] map = new int[inputInfo[0]][inputInfo[1]];
        for (int i = 0; i < map.length; i++) {
            map[i] = toIntArray(br.readLine());
        }

        int[][] answer = new Solution(inputInfo[0], inputInfo[1], map).solution();
        StringBuilder result = new StringBuilder();

        for (int[] line : answer) {
            for (int num : line) {
                result.append(num).append(" ");
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    private static int[] toIntArray(String inputStr) {
        return Arrays.stream(inputStr.split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}