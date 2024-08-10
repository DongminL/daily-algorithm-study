import java.io.*;
import java.util.*;

public class Main {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int n;    // 도시의 수
    int m;    // 여행 계획에 속한 도시의 수
    int[][] cities;    // i번째 도시와 j번째 도시의 연결 여부 (인접 행렬)
    int[] route;    // 여행 계획 루트
    int[] relation;    // 각 도시가 속한 집합의 대표자
    
    /*
        두 원소가 포함된 집합을 합침
        B 집합의 대표자를 A 집합의 대표자로 연결
    */
    private void union(int a, int b) {
        int representA = find(a);   // 원소 a가 포함된 집합의 대표자
        int representB = find(b);   // 원소 b가 포함된 집합의 대표자

        // 서로 다른 집합일 때만 변경
        if (representA != representB) {
            relation[representB] = representA;
        }
    }

    /* 원소가 속한 집합의 대표자 반환 */
    private int find(int element) {
        // 대표자일 때
        if (element == relation[element]) {
            return element;
        }

        // 대표자가 아니면 대표자를 찾아감
        return relation[element] = find(relation[element]);
    }
    
    /* 여행 계획 가능 여부 */
    private boolean checkRoute() {
        int represent = find(route[0]-1);    // 첫 번째 도시가 속한 집합의 대표자
        
        // 대표자가 다르면 서로 연결되지 않은 경우이기 때문에 불가능
        for (int i = 1; i < route.length; i++) {
            if (represent != find(route[i]-1)) {
                return false;
            }
        }
        
        return true;
    }
    
    private void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        // 인접 행렬 구성
        cities = new int[n][n];
        for(int i = 0; i < n; i++) {
            cities[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        
        // 여행 루트 배열로 만들기
        route = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        
        // 개별 집합으로 초기화
        relation = new int[n];
        for (int i = 0; i < n; i++) {
            relation[i] = i;
        }
        
        // 연결된 도시 union
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cities[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        if (checkRoute()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}