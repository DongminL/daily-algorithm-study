import java.io.*;
import java.util.*;

public class Main {
    
    static boolean[] visited;    // 방문한 노드 체크
    static ArrayList<Integer>[] list;    // 인접 리스트
    static boolean completed = false;    // 연속된 5명의 관게 존재 여부
    
    /* 5명의 연속적인 친구 관계 찾기 */
    static int searchRelation(int n) {
        int cnt = 0;    // 연속적인 관계의 수
        
        // 각 사람마다 연속된 5명의 친구 관계가 있는지 탐색
        for (int i = 0; i < n; i++) {
            dfs(i, cnt);
            if (completed) {
                break;
            }
        }
        
        return completed ? 1 : 0;
    }
    
    /* DFS (깊이 우선 탐색) */
    static void dfs(int v, int cnt) {
        // 연속된 4개의 관계가 존재할 때, 종료
        if (cnt == 4 || completed) {
            completed = true;
            return;
        }
        
        
        // 방문하지 않은 경우
        visited[v] = true;
        // 해당 노드의 인접 리스트를 DFS
        list[v].stream()
            .filter(p -> !visited[p])
            .forEach(p -> dfs(p, cnt+1));
        
        // 연속된 5명을 못찾았을 경우, 해당 노드들의 방문 초기화
        visited[v] = false;    
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());    // 사람의 수
        int m = Integer.parseInt(st.nextToken());    // 친구 관계의 수
        
        // 배열 및 인접 리스트 초기화
        visited = new boolean[n];
        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<Integer>();
        }
        
        // 인접 리스트 구성
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int person1 = Integer.parseInt(st.nextToken());    // 친구 관계의 A
            int person2 = Integer.parseInt(st.nextToken());    // 친구 관계의 B
            
            // 친구 관계 설정
            list[person1].add(person2);
            list[person2].add(person1);
        }
        
        System.out.println(searchRelation(n));
    }
}