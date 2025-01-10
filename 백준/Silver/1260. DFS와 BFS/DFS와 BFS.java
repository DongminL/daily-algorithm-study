import java.io.*;
import java.util.*;

class Solution {

    private List<Integer>[] graph;  // 주어진 엣지에 따른 인접 리스트
    private boolean[] visited;  // 각 노드에 방문 여부
    private StringBuilder answer = new StringBuilder(); // 결과 출력

    public void solution(int n, int m, int startNode, int[][] edges) {
        // 인접 리스트 만들기
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            // 양방향 엣지
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // 번호가 작은 노드부터 방문
        for (int i = 1; i <= n; i++) {
            graph[i].sort(Comparator.naturalOrder());
        }

        visited = new boolean[n + 1];
        dfs(startNode);
        answer.append("\n");
        visited = new boolean[n + 1];
        bfs(startNode);

        System.out.println(answer);
    }

    private void dfs(int node) {
        visited[node] = true;
        answer.append(node).append(" ");

        // 아직 방문하지 않은 주위 노드로 이동
        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    private void bfs(int startNode) {
        Queue<Integer> queue = new ArrayDeque<>();

        // 시작 노드 설정
        queue.offer(startNode);
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll(); // 현재 위치
            answer.append(current).append(" ");

            // 아직 방문하지 않은 주위 노드로 이동
            for (int next : graph[current]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 0: 정점의 개수 n, 1: 간선의 개수 m, 2: 탐색을 시작할 정점의 번호 v
        int[] graphInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();

        // [i][0] <-> [i][1] 엣지
        int[][] edges = new int[graphInfo[1]][2];
        for (int i = 0; i < graphInfo[1]; i++) {
            edges[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        new Solution().solution(graphInfo[0], graphInfo[1], graphInfo[2], edges);
    }
}