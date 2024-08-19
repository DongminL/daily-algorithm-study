import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int n;    // 정점의 개수
    int m;    // 간선의 개수
    int start;    // 시작 정점
    ArrayList<Vertex>[] list;    // 특정 정점 u에서 갈 수 있는 정점들의 리스트 (인접 리스트)
    int[] shortestDistance;    // 시작 정점부터 각 정점까지의 가장 최단 거리

    /* 시작점부터 각 정점까지의 최단 경로 구하기 (다익스트라) */
    private void dijkstra() {
        boolean[] visited = new boolean[n+1];  // 방문한 노드 체크
        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        // 초기값 설정
        shortestDistance[start] = 0;
        pq.offer(new Vertex(start, 0));

        // 연결된 정점들 탐색
        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();    // 현재 위치한 정점

            // 이미 방문한 노드인 경우 패스
            if (visited[cur.num]) {
                continue;
            }

            visited[cur.num] = true;

            // 다음 정점까지의 최단 거리 구하기
            list[cur.num].stream()
                    .filter(next -> shortestDistance[cur.num] + next.weight < shortestDistance[next.num])   // 최단 경로를 갱신할 수 있을 때만 탐색
                    .forEach(next -> {
                        shortestDistance[next.num] = shortestDistance[cur.num] + next.weight;   // 최단 경로 갱신

                        // 갱신된 거리로 설정한 후 다음 노드부터 탐색하도록 추가
                        pq.offer(new Vertex(next.num, shortestDistance[next.num]));
                    });
        }
    }

    private void solution() throws IOException {
        // 첫 번째 줄에 대한 입력값
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 두 번째 줄에 대한 입력값
        start = Integer.parseInt(br.readLine());

        // 배열 및 리스트 초기화
        list = new ArrayList[n+1];
        shortestDistance = new int[n+1];
        IntStream.range(1, n+1).forEach(i -> {
            list[i] = new ArrayList<>();
            shortestDistance[i] = Integer.MAX_VALUE;
        });

        // 간선에 대한 정보에 대한 입력값
        IntStream.range(0, m).forEach(i -> {
            try {
                // u에서 v로 가는 가중치 w인 간선에 대한 입력값
                st = st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                list[u].add(new Vertex(v, w));    // 인접 리스트에 추가

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        dijkstra();    // 각 정점의 최단 경로 구하기

        // 결과값 출력
        IntStream.range(1, n+1)
                .mapToObj(i -> shortestDistance[i] == Integer.MAX_VALUE ?
                        "INF" : String.valueOf(shortestDistance[i]))
                .forEach(System.out::println);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

/* 정점에 대한 클래스 */
class Vertex implements Comparable<Vertex> {
    int num;    // 정점의 번호
    int weight;    // 정점의 가중치

    public Vertex(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }

    // 오름차순
    public int compareTo(Vertex o) {
        return Integer.compare(this.weight, o.weight);
    }
}