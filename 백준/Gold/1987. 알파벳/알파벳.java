import java.io.*;
import java.util.*;

class Solution {

    private final int r;
    private final int c;
    private final char[][] board;

    // 말이 지날 수 있는 최대의 칸 수
    private int answer = 0;

    // 북, 동, 남, 서 방향
    private static final int[] dX = { 0, 1, 0, -1 };
    private static final int[] dY = { -1, 0, 1, 0 };

    public Solution(int r, int c, char[][] board) {
        this.r = r;
        this.c = c;
        this.board = board;
    }

    int solution() {
        // 알파벳 방문 여부
        boolean[] visited = new boolean[26];

        // 시작 지점 설정
        visited[board[0][0] - 'A'] = true;
        move(0, 0, visited, 1);

        return answer;
    }
    
    private void move(int x, int y, boolean[] visited, int count) {
        // 이동한 칸 수 갱신
        answer = Math.max(answer, count);

        // 4 방향으로 전진
        for (int i = 0; i < 4; i++) {
            int nextX = x + dX[i];
            int nextY = y + dY[i];

            // 이미 방문한 알파벳은 제외
            if (isBoard(nextX, nextY) && !visited[board[nextY][nextX] - 'A']) {
                visited[board[nextY][nextX] - 'A'] = true;
                move(nextX, nextY, visited, count + 1);
                visited[board[nextY][nextX] - 'A'] = false;
            }
        }
    }

    // 해당 위치가 보드 위인지 확인
    private boolean isBoard(int x, int y) {
        return 0 <= x && x < c && 0 <= y && y < r;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        char[][] board = new char[inputInfo[0]][inputInfo[1]];
        for (int i = 0; i < inputInfo[0]; i++) {
            board[i] = br.readLine().toCharArray();
        }

        System.out.println(new Solution(inputInfo[0], inputInfo[1], board).solution());
    }
}