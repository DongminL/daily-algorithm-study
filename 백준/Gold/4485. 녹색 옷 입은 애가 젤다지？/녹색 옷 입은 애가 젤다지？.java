import java.io.*;
import java.util.*;

class Solution {

    private final int n;
    private final int[][] thieves;

    public Solution(int n, int[][] thieves) {
        this.n = n;
        this.thieves = thieves;
    }

    int solution() {
        // 잃을 수 밖에 없는 최소 비용
        int[][] minCosts = new int[n][n];
        for (int[] minCost : minCosts) {
            Arrays.fill(minCost, 1_000_000_000);
        }

        // 북, 동, 남, 서 방향
        int[] dX = { 0, 1, 0, -1 };
        int[] dY = { -1, 0, 1, 0 };

        Queue<Node> pq = new PriorityQueue<>();

        // 시작 지점 설정
        minCosts[0][0] = thieves[0][0];
        pq.offer(new Node(0, 0, thieves[0][0]));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // 비용이 갱신되기 이전인 경우, 패스
            if (current.cost > minCosts[current.y][current.x]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dX[i];
                int nextY = current.y + dY[i];

                // 동굴이 아닌 경우, 패스
                if (!isCave(nextX, nextY)) {
                    continue;
                }

                // 최소 비용 갱신
                if (minCosts[current.y][current.x] + thieves[nextY][nextX] < minCosts[nextY][nextX]) {
                    minCosts[nextY][nextX] = minCosts[current.y][current.x] + thieves[nextY][nextX];
                    pq.offer(new Node(nextX, nextY, minCosts[nextY][nextX]));
                }
            }
        }

        return minCosts[n - 1][n - 1];
    }

    // 해당 위치가 동굴인지 확인
    private boolean isCave(int x, int y) {
        return -1 < x && x < n && -1 < y && y < n;
    }

    private static class Node implements Comparable<Node> {

        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder result = new StringBuilder();

        int problemNumber = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());

            // 입력 종료
            if (n == 0) {
                break;
            }

            int[][] thieves = new int[n][n];
            for (int i = 0; i < n; i++) {
                thieves[i] = toIntArray(br.readLine().split(" "));
            }

            int answer = new Solution(n, thieves).solution();

            result.append("Problem ").append(problemNumber++).append(": ")
                .append(answer).append("\n");
        }

        System.out.println(result);
    }

    private static int[] toIntArray(String[] strArray) {
        return Arrays.stream(strArray)
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}