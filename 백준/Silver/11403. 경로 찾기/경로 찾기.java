import java.io.*;
import java.util.*;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder result = new StringBuilder();
    StringTokenizer st;

    int n;    // 정점의 개수
    int[][] matrix;    // 인접행렬
    int[][] edges;    // i에서 j로 가는 경로 여부 (0: 없음, 1: 있음)
    boolean[] visited;    // 방문한 노드 체크

    /* BFS (깊이 우선 탐색) */
    private void bfs(int start) {
        Deque<Integer> queue = new ArrayDeque<>();

        queue.offerLast(start);    // 시작 노드

        // 탐색 시작
        while (!queue.isEmpty()) {
            int node = queue.pollFirst();    // 현재 위치한 노드

            for (int i = 0; i < n; i++) {
                // 간선이 연결되어 있고, 아직 방문하지 않은 노드 방문
                if (matrix[node][i] == 1 && visited[i] == false) {
                    queue.offerLast(i);
                    visited[i] = true;

                    edges[start][i] = 1;    // start부터 i까지 간선이 연결되어 있으니 추가
                }
            }
        }
    }

    private void solution() throws IOException {
        n = Integer.parseInt(br.readLine());    // 정점의 개수

        // 인접행렬 생성
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        edges = matrix.clone(); // 인접 행렬 복사

        // 각 노드별 간선 탐색
        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            bfs(i);
        }

        // 결과값 행렬로 만들기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result.append(edges[i][j] + " ");
            }
            result.append("\n");    // 다음 행으로
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}