import java.io.*;
import java.util.*;

class Solution {

    private int n;
    private int m;
    private int x;
    private List<Village>[] roads;

    public Solution(int n, int m, int x, int[][] roads) {
        this.n = n;
        this.m = m;
        this.x = x;
        this.roads = toAdjacencyList(roads);
    }

    int solution() {
        // N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간
        int answer = 0;

        // 각 학생들이 각자 집에서 출발하여 각 마을에 도착하는 최단 시간 기록용
        int[][] startHome = new int[n + 1][n + 1];
        // 각 학생들이 x에서 각 마을에 도착하는 최단 시간 기록용
        int[][] startX = new int[n + 1][n + 1];

        // 기록용 배열 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(startHome[i], 100_000_000);
            Arrays.fill(startX[i], 100_000_000);
        }

        // 각 마을에서 학생들 출발
        for (int i = 1; i <= n; i++) {
            dijastra(i, startHome[i]);
        }

        // x에서 학생들 출발
        for (int i = 1; i <= n; i++) {
            dijastra(x, startX[i]);
        }

        // 가장 오래 걸린 소요시간 구하기
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, startHome[i][x] + startX[i][i]);
        }

        return answer;
    }

    // 특정 지점부터 출발할 때의 각 마을까지 최단 시간 구하기
    private void dijastra(int start, int[] minTimes) {
        // 경과 시간을 기준으로 하는 우선순위 큐 (Heap)
        Queue<Village> heap = new PriorityQueue<>(Comparator.comparing(e -> e.time));

        // 시작 지점 설정
        heap.offer(new Village(start, 0));
        minTimes[start] = 0;

        while (!heap.isEmpty()) {
            Village current = heap.poll();

            if (minTimes[current.number] < current.time) {
                continue;
            }

            // 다음 마을 이동
            for (Village next : roads[current.number]) {
                int sendTime = minTimes[current.number] + next.time;   // 다음 마을까지 소요시간

                // 최단 시간 갱신 및 다음 마을로 이동
                if (sendTime < minTimes[next.number]) {
                    minTimes[next.number] = sendTime;
                    heap.offer(new Village(next.number, sendTime));
                }
            }
        }
    }

    // 2차원 배열을 인접 리스트로 변환 (단방향 매핑)
    private List<Village>[] toAdjacencyList(int[][] roads) {
        List<Village>[] result = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            result[road[0]].add(new Village(road[1], road[2]));
        }

        return result;
    }

    private static class Village {

        int number; // 해당 마을의 번호
        int time;   // 해당 마을까지 걸리는 시간

        public Village(int number, int time) {
            this.number = number;
            this.time = time;
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = toIntArray(br.readLine().split(" "));

        int[][] roads = new int[inputInfo[1]][3];
        for (int i = 0; i < roads.length; i++) {
            roads[i] = toIntArray(br.readLine().split(" "));
        }

        System.out.println(new Solution(inputInfo[0], inputInfo[1], inputInfo[2], roads).solution());
    }

    private static int[] toIntArray(String[] strArray) {
        return Arrays.stream(strArray)
            .mapToInt(Integer::parseInt)
            .toArray();
    }
}