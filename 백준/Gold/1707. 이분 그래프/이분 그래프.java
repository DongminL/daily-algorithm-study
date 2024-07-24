import java.io.*;
import java.util.*;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder result = new StringBuilder();    // 출력할 결과
    StringTokenizer st;

    ArrayList<Integer>[] list;    // 인접 리스트
    boolean[] visited;    // 방문한 노드 체크
    int[] group;    // 두 개의 집합으로 분류하는 표 (0 or 1)
    boolean isEvenGraph;    // 이분 그래프인지 여부

    /* DFS (깊이 우선 탐색) */
    private void dfs(int v) {
        visited[v] = true;

        for (int node : list[v]) {
            if (!visited[node]) {
                group[node] = (group[v] + 1) % 2;    // v에 인접한 노드는 다른 집합으로 분류
                dfs(node);
            } else if (group[node] == group[v]) {
                // 이미 방문한 노드가 현재 노드와 같은 집합이면 종료
                isEvenGraph = false;
                return;
            }
        }
    }

    private void solution() throws IOException {
        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());    // 각 테스트의 정점의 개수
            int E = Integer.parseInt(st.nextToken());    // 각 테스트의 간선의 개수

            // 각 배열 및 리스트 초기화
            visited = new boolean[V+1];
            group = new int[V+1];
            isEvenGraph = true;
            list = new ArrayList[V+1];
            for (int j = 1; j <= V; j++) {
                list[j] = new ArrayList<>();
            }

            // 각 테스트별 인접 리스트 구성
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());    // 출발 노드
                int end = Integer.parseInt(st.nextToken());    // 도착 노드

                list[start].add(end);
                list[end].add(start);
            }

            // 이분 그래프인지 확인
            for (int j = 1; j <= V; j++) {
                if (isEvenGraph) {
                    dfs(j);
                } else {
                    break;
                }
            }

            // 이분 그래프인지 결과값 추가
            if (isEvenGraph) {
                result.append("YES").append("\n");
            } else {
                result.append("NO").append("\n");
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}