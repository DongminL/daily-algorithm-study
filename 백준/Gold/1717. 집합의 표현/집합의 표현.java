import java.io.*;
import java.util.*;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder result = new StringBuilder();
    StringTokenizer st;

    int n;    // 원소의 최대값
    int m;    // 연산의 개수
    int[] relation;    // 각 원소가 속한 집합의 대표자

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

    private void solution() throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 개별 집합으로 초기화
        relation = new int[n+1];
        for (int i = 1; i <= n; i++) {
            relation[i] = i;
        }

        // 입력받은 연산 수행
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int flag = Integer.parseInt(st.nextToken());    // 0: Union, 1: 같은 집합인지 확인
            int a = Integer.parseInt(st.nextToken());    // 원소 A
            int b = Integer.parseInt(st.nextToken());    // 원소 B

            if (flag == 0) {
                union(a, b);
            } else {
                // 서로의 대표자가 같아 같은 집합일 때
                if (find(a) == find(b)) {
                    result.append("YES\n");

                } else {
                    result.append("NO\n");
                }
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}