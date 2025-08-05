import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int k) {
        // 수빈이가 동생을 찾는 가장 빠른 시간
        int answer = 0;

        // 각 지점에 방문 여부
        boolean[] visited = new boolean[100_001];

        // 최단 시간 순으로 정렬
        Queue<Point> heap = new PriorityQueue<>(Comparator.comparingInt(p -> p.time));
        // 수빈이의 처음 위치
        heap.offer(new Point(n, 0));

        // 동생 찾기
        while (!heap.isEmpty()) {
            Point current = heap.poll();
            visited[current.location] = true;

            // 동생 위치에 도착한 경우
            // 가장 먼저 도착한 경우가 최단 시간 (Heap 으로 정렬하고 있기 때문)
            if (current.location == k) {
                answer = current.time;
                break;
            }

            int[] nextPoints = { current.location - 1, current.location + 1, 2 * current.location };
            for (int i = 0; i < 3; i++) {
                if (!movable(nextPoints[i], visited)) {
                    continue;
                }

                // 걷는 경우
                if (i != 2) {
                    heap.offer(new Point(nextPoints[i], current.time + 1));
                } else {
                    // 순간이동한 경우
                    heap.offer(new Point(nextPoints[i], current.time));
                }
            }
        }

        return answer;
    }

    // 이동 가능한 지점인지 확인
    private boolean movable(int point, boolean[] visited) {
        return 0 <= point && point <= 100_000 && !visited[point];
    }

    private static class Point {

        int location;   // 현재 위치
        int time;   // 현재까지 걸린 시간

        public Point(int location, int time) {
            this.location = location;
            this.time = time;
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1]));
    }
}