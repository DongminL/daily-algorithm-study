import java.io.*;
import java.util.*;

public class Main {
    
    static boolean[] visited;    // 방문 여부 (2차원 -> 1차원)
    static int[] maze;    // 미로 배열 (2차원 -> 1차원)
    static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};    // 방향 이동 시 좌표 변화 (북, 동, 남, 서)
    
    // BFS (너비 우선 탐색)
    static void bfs(int n, int m, int x, int y) {
        Deque<Integer> queue = new ArrayDeque<>();
        
        // 초기 방문 기록
        queue.offerLast(m*x + y);
        visited[m*x + y] = true;
        
        // 탐색 시작
        while (!queue.isEmpty()) {
            int now = queue.pollFirst();    // 현재 위치 (2차원 -> 1차원으로 낮춘 위치 값)
            
            // 모든 방향 살펴보기
            for (int i = 0; i < 4; i++) {
                int nextX = (now / m) + direction[i][0];    // 이동할 방향의 x 좌표
                int nextY = (now % m) + direction[i][1];    // 이동할 방향의 y 좌표
                
                // 유효한 좌표만 허용
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m) {
                    // 방문하지 않은 좌표일 때
                    if (!visited[m*nextX + nextY]) {
                        visited[m*nextX + nextY] = true;
                        queue.offerLast(m*nextX + nextY);
                        
                        maze[m*nextX + nextY] =  maze[now] + 1; 
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());    // 미로의 행의 개수
        int m = Integer.parseInt(st.nextToken());    // 미로의 열의 개수
        
        // 배열 크기 설정
        maze = new int[n * m];    
        visited = new boolean[n * m];
        
        // 미로 구성
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), "");
            
            String row = st.nextToken();    // 미로의 한 행
            for (int j = 0; j < m; j++) {
                int isMove = Integer.parseInt(row.substring(j, j+1));    // 해당 칸의 이동 여부 (1: 가능, 0: 불가능)
                maze[m*i + j] = isMove;
                
                // 이동 불가능한 칸은 BFS 시, 방문 못하게 변경
                if (maze[m*i + j] == 0) {
                    visited[m*i + j] = true;
                }
            }
        }
        
        bfs(n, m, 0, 0);    // 미로의 시작 좌표부터 BFS 탐색
        
        System.out.println(maze[n * m - 1]);    // 이동하는 칸의 수의 최소값
    }
}