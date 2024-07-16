import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    
    static ArrayList<Vertex>[] list;    // 인접 리스트
    static boolean[] visited;    // 방문한 노드 체크
    static int[] distances;    // 노드 간의 누적 거리
    static int max = 1;    // 가장 먼 거리를 갖는 노드의 번호

    /* v로부터 가장 먼 노드 찾기 */
    static void diameter(int v) {
        Deque<Integer> queue = new ArrayDeque<>();

        // v 노드 방문
        queue.offerLast(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int now = queue.pollFirst();    // 현재 위치한 노드

            // BFS 탐색
            list[now].stream()
                    .filter(node -> !visited[node.getNum()])
                    .forEach(node -> {
                        // 다음 노드 방문
                        queue.offerLast(node.getNum());
                        visited[node.getNum()] = true;

                        distances[node.getNum()] = distances[now] + node.getDistance();    // 거리 누적
                    });
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int v = Integer.parseInt(br.readLine());    // 정점의 개수

        // 방문 및 인접 리스트 초기화
        visited = new boolean[v+1];
        distances = new int[v+1];
        list = new ArrayList[v+1];
        for (int i = 1; i <= v; i++) {
            list[i] = new ArrayList<>();
        }

        // 인접 리스트 구성
        for (int i = 1; i <= v; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());

                if (num == -1) {
                    break;
                }

                // 목적지 정점까지에 대한 정보를 인접 리스트에 추가
                Vertex dv = new Vertex(num, Integer.parseInt(st.nextToken()));
                list[start].add(dv);
            }
        }

        diameter(1);    // 1번 노드부터 탐색

        // 1로부터 가장 먼 노드 번호 알아내기
        IntStream.range(2, distances.length)
                .filter(index -> distances[max] < distances[index])
                .forEach(index -> max = index);

        // 가장 먼 노드에서 재탐색
        visited = new boolean[v+1];
        distances = new int[v+1];
        diameter(max);

        System.out.println(Arrays.stream(distances).max().getAsInt());   // 트리의 지름 출력
    }
}

/* 목적지 정점에 대한 정보 */
class Vertex {
    
    private int num;    // 목적지 정점의 번호
    private int distance;    // 정점까지의 거리
    
    /* 생성자 */
    public Vertex(int num, int distance) {
        this.num = num;
        this.distance = distance;
    }
    
    /* 각 변수의 Getter */
    public int getNum() {
        return this.num;
    }
    
    public int getDistance() {
        return this.distance;
    }
}