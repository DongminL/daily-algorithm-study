import java.io.*;
import java.util.*;

class Node {
    
    int num;    // 해당 노드의 번호
    int cnt;    // 해당 노드까지 이동 횟수
    
    public Node(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}

public class Main {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder result = new StringBuilder();
    StringTokenizer st;
    
    int n;    // 회원의 수
    ArrayList<Integer>[] list;    // 친구 관계의 인접 리스트
    boolean[] visited;    // 방문한 노드 체크
    int[] scores;    // 각 회원의 점수
    int min = Integer.MAX_VALUE;    // 회원 점수의 최솟값
    int cnt = 0;    // 후보 인원
    
    /* 각 회원의 최대 이동 횟수 BFS */
    private int bfs(int start) {
        Deque<Node> queue = new ArrayDeque<>();
        int count = 0;    // 해당 노드의 최대 이동 횟수
        
        // 시작 노드 방문
        queue.offerLast(new Node(start, 0));
        visited[start] = true;
        
        // 탐색 시작
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            // 방문하지 않은 노드 탐색
            list[node.num].stream()
                .filter(next -> !visited[next])
                .forEach(next -> {
                    queue.offerLast(new Node(next, node.cnt + 1));
                    visited[next] = true;
                });
            count = Math.max(count, node.cnt);
        }
        
        return count;
    }
    
    private void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        
        // 배열 및 리스트 초기화
        scores = new int[n+1];
        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        // 친구 관계 입력
        while (true) {
            st = new StringTokenizer(br.readLine());
            
            int p1 = Integer.parseInt(st.nextToken());    // 친구 1
            int p2 = Integer.parseInt(st.nextToken());    // 친구 2
            
            if (p1 == -1 && p2 == -1) {
                break;
            }
            
            // 친구 관계 추가
            list[p1].add(p2);
            list[p2].add(p1);
        }
        
        // 모든 회원의 점수 매기기
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            int maxScore = bfs(i);    // 해당 회원의 최대 점수
            
            scores[i] = maxScore;
            min = Math.min(min, maxScore);
        }
        
        // 후보의 점수와 인원
        Arrays.stream(scores)
            .filter(s -> s == min)
            .forEach(s -> cnt++);
        result.append(min + " " + cnt + "\n");
        
        // 회장 후보 오름차순
        for (int i = 1; i <= n; i++) {
            if (scores[i] == min) {
                result.append(i + " ");
            }
        }
        
        System.out.println(result);
    }
    
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}