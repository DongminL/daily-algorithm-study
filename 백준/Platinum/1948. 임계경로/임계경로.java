import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder result = new StringBuilder();

    int n;    // 도시의 개수
    int m;    // 도로의 개수
    int start;    // 출발 도시
    int end;    // 도착 도시
    ArrayList<Road>[] paths;    // 각 도시까지의 경로들 (인접 리스트)
    ArrayList<Road>[] reversePaths;    // 역인접 리스트
    int[] frontCities;    // 각 도시의 앞에 있는 도시의 개수    (진입 차수 배열)

    /* 달려야 하는 도로의 수 세기 (위상 정렬) */
    private int countRoad(int end, int[] times) {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];    // 각 도시를 방문한 여부
        int[] count = {0};    // 임계 경로의 도로의 수

        // 종료 도시 큐에 삽입
        queue.offerLast(end);

        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();    // 현재 위치한 도시

            // 역방향으로 임계 경로를 탐색 (road: 다음 도시로 이어진 도로)
            reversePaths[cur].stream()
                    // 임계 경로에 해당하는 경우
                    .filter(road -> times[cur] == times[road.dest] + road.hour)
                    .peek(road -> count[0]++)  // 도로의 수 +1 증가
                    .filter(road -> !visited[road.dest])  // 아직 방문하지 않은 도시일 때
                    .forEach(road -> {
                        queue.offerLast(road.dest);
                        visited[road.dest] = true;
                    });
        }

        return count[0];
    }

    /* 임계 경로 구하기 (위상 정렬) */
    private int[] criticalPath(int start) {
        Deque<Integer> queue = new ArrayDeque<>();
        int[] times = new int[n+1];    // 각 도시까지 임계 경로의 시간

        // 시작 도시 큐에 삽입
        queue.offerLast(start);

        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();    // 현재 위치한 도시

            // 다음 도시를 탐색 (road: 다음 도시로 이어진 도로)
            paths[cur].stream()
                    .peek(road -> {
                        // 현재 도시를 벗어나기에 앞에 있는 도시 수 -1
                        frontCities[road.dest]--;
                        // 각 도시의 임계 경로의 시간 갱신
                        times[road.dest] = Math.max(times[road.dest], times[cur] + road.hour);
                    })
                    .filter(road -> frontCities[road.dest] == 0)
                    .forEach(road -> queue.offerLast(road.dest));
        }

        return times;
    }

    private void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 배열 및 리스트 초기화
        frontCities = new int[n+1];
        paths = new ArrayList[n+1];
        reversePaths = new ArrayList[n+1];
        IntStream.range(1, n+1).forEach(i -> {
            paths[i] = new ArrayList<>();
            reversePaths[i] = new ArrayList<>();
        });

        // 도로에 대한 정보 입력 받기
        IntStream.range(0, m).forEach(i -> {
            try {
                // 입력 받은 정보 (0번째: 출발 도시, 1번째: 도착 도시, 2번째: 가는데 걸리는 시간)
                int[] input = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

                // 도시들의 정보 추가
                paths[input[0]].add(new Road(input[1], input[2]));
                reversePaths[input[1]].add(new Road(input[0], input[2]));
                frontCities[input[1]]++;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // 0번째: 시작 도시, 1번째: 종료 도시
        String[] startEnd = br.readLine().split(" ");
        start = Integer.parseInt(startEnd[0]);
        end = Integer.parseInt(startEnd[1]);

        /*
          임계경로 결과값 출력
          (1번째 줄: 만나는 시간을, 2번째 줄: 달려야 하는 도로의 수)
        */
        int[] times = criticalPath(start);
        result.append(times[end] + "\n");

        int count = countRoad(end, times);
        result.append(count + "\n");

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

class Road {
    int dest;    // 도착지
    int hour;    // 도로를 지나는데 걸리는 시간

    public Road(int dest, int hour) {
        this.dest = dest;
        this.hour = hour;
    }
}