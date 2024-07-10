import java.io.*;
import java.util.*;

public class Main {
    
    static boolean[] visited;    // 방문한 노드 체크
    static ArrayList[] list;    // 인접 리스트

    /* 연결 요소의 개수 구하기 */
    static int counting(int n) {
        int cnt = 0;    // 연결 요소의 개수

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i);
                cnt++;
            }
        }

        return cnt;
    }

    /*깊이 우선 탐색 (DFS) */
    static void dfs(int v) {
        // v를 이미 방문 했으면 그냥 리턴
        if (visited[v]) {
            return;
        }
        // 방문 하지않은 노드를 dfs로 방문하기
        visited[v] = true;
        list[v].stream()
                .filter(u -> !visited[(int) u])
                .forEach(u -> dfs((int) u));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());    // 정점의 개수
        int m = Integer.parseInt(st.nextToken());    // 간선의 개수

        // 배열 및 리스트 초기화
        visited = new boolean[n+1];
        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<Integer>();
        }

        // 인접 리스트 구성
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());    // 엣지의 시작점
            int v = Integer.parseInt(st.nextToken());    // 엣지의 끝점

            // 양방향 엣지이므로 각각 추가
            list[u].add(v);
            list[v].add(u);
        }

        System.out.println(counting(n));
    }
}