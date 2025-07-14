import java.io.*;

class Solution {

    private int n;
    private String balls;

    public Solution(int n, String balls) {
        this.n = n;
        this.balls = balls;
    }

    int solution() {
        // 볼을 이동하여 같은 색끼리 모았을 때 최소 이동횟수
        int answer = Integer.MAX_VALUE;

        // 색깔별 개수
        int redCount = 0;
        int blueCount = 0;
        for (int i = 0; i < n; i++) {
            if (balls.charAt(i) == 'R') {
                redCount++;
            } else {
                blueCount++;
            }
        }

        // 빨간공 오른쪽으로 옮기기
        answer = Math.min(answer, moveToRight('R', redCount, blueCount));

        // 빨간공 왼쪽으로 옮기기
        answer = Math.min(answer, moveToLeft('R', redCount, blueCount));

        // 파란공 오른쪽으로 옮기기
        answer = Math.min(answer, moveToRight('B', redCount, blueCount));

        // 파란공 왼쪽으로 옮기기
        answer = Math.min(answer, moveToLeft('B', redCount, blueCount));

        return answer;
    }

    // (color)색 공을 왼쪽으로 옮기는 횟수
    private int moveToLeft(char color, int redCount, int blueCount) {
        int firstCount = 0; // 왼쪽 처음 연속되는 color 색의 개수

        for (int i = 0; i < n; i++) {
            if (balls.charAt(i) == color) {
                firstCount++;
            } else {
                break;
            }
        }

        // 움직이는 공의 개수
        return color == 'R' ?
            redCount - firstCount : blueCount - firstCount;
    }

    // (color)색 공을 오른쪽으로 옮기는 횟수
    private int moveToRight(char color, int redCount, int blueCount) {
        int firstCount = 0; // 오른쪽 처음 연속되는 color 색의 개수

        for (int i = n - 1; i > -1; i--) {
            if (balls.charAt(i) == color) {
                firstCount++;
            } else {
                break;
            }
        }

        // 움직이는 공의 개수
        return color == 'R' ?
            redCount - firstCount : blueCount - firstCount;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String balls = br.readLine();

        System.out.println(new Solution(n, balls).solution());
    }
}