import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    
    static boolean[] visited;
    static ArrayList<Integer>[] list;    // 인접 리스트
    static int[] distances;    // 시작 도시부터 각 도시까지의 거리

    /* BFS로 v 도시부터 각 도시까지의 거리 구하기 */
    static void bfs(int v) {
        Deque<Integer> queue = new ArrayDeque<>();

        // v 도시 방문
        queue.offerLast(v);
        visited[v] = true;

        // BFS 탐색
        while (!queue.isEmpty()) {
            int now = queue.pollFirst();    // 현재 위치한 도시

            // 방문하지 않은 다음 도시로 이동
            list[now].stream().filter(node -> !visited[node])
                    .forEach(node -> {
                        queue.offerLast(node);
                        visited[node] = true;

                        distances[node] = distances[now] + 1;    // 다음 도시까지의 거리
                    });
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());    // 도시의 개수
        int m = Integer.parseInt(st.nextToken());    // 도로의 개수
        int k = Integer.parseInt(st.nextToken());    // 거리 정보
        int x = Integer.parseInt(st.nextToken());    // 출발 도시의 번호

        // 배열 및 리스트 초기화
        visited = new boolean[n+1];
        distances = new int[n+1];
        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 인접 리스트 구성
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());    // 도로의 시작 도시
            int end = Integer.parseInt(st.nextToken());    // 도로의 끝 도시

            // 단방향 도로로 인접 리스트에 추가
            list[start].add(end);
        }

        bfs(x);    // 특정 거리의 도시 찾기

        // 특정 거리의 도시 찾기
        StringBuilder result = new StringBuilder();
        IntStream.range(1, distances.length)
                .filter(city -> distances[city] == k)
                .forEach(city -> result.append(city).append("\n"));

        // 결과 출력
        if (result.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}