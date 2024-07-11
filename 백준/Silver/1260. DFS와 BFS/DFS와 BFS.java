import java.io.*;
import java.util.*;

public class Main {
    
    static boolean[] visited;    // 방문한 노드 체크
    static ArrayList<Integer>[] list;    // 인접 리스트

    static String search(int n, int v) {
        // 번호가 작은 노드부터 방문
        for (int i = 1; i <= n; i++) {
            list[i].sort(Comparator.naturalOrder());
        }

        dfs(v); // DFS
        visited = new boolean[n+1]; // 방문 리스트 초기화
        String bfsResult = bfs(v); // BFS

        return "\n" + bfsResult;
    }

    /* BFS (너비 우선 탐색) */
    static String bfs(int v) {
        StringBuilder sb = new StringBuilder();
        Deque<Integer> queue = new ArrayDeque<>();;    // BFS의 Queue

        queue.offerLast(v);
        visited[v] = true;

        // BFS
        while (!queue.isEmpty()) {
            int node = queue.pollFirst();   // FIFO
            sb.append(node).append(" ");

            // 인접 리스트 탐색
            list[node].stream()
                    .filter(e -> !visited[e])
                    .forEach(e -> {
                visited[e] = true;
                queue.offerLast(e);
            });
        }

        return sb.toString();
    }

    /* DFS (깊이 우선 탐색) */
    static void dfs(int v) {
        // 방문 안 한 노드를 탐색
        visited[v] = true;
        System.out.print(v + " ");
        list[v].stream()
                .filter(e -> !visited[e])
                .forEach(e -> dfs(e));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());    // 정점의 개수
        int m = Integer.parseInt(st.nextToken());    // 간선의 개수
        int v = Integer.parseInt(st.nextToken());    // 시작할 노드의 번호

        // 배열 및 인접 리스트 초기화
        visited = new boolean[n+1];
        list = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        // 인접 리스트 구성
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());    // 간선의 시작 노드
            int e = Integer.parseInt(st.nextToken());    // 간선의 끝 노드

            // 양방향 엣지
            list[s].add(e);
            list[e].add(s);
        }

        System.out.println(search(n, v));
    }
}