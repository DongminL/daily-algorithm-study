import java.io.*;
import java.util.*;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[] sizes;    // 각 물통의 크기 (0: A, 1: B, 2: C)
    boolean[][][] visited;  // 각 물통에 담길 양의 경우의 수
    ArrayList<Integer> result = new ArrayList<>();    // 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양

    /* 경우의 수 BFS (너비 우선 탐색) */
    private void bfs() {
        Deque<Bottles> queue = new ArrayDeque<>();
        queue.offerLast(new Bottles(0, 0, sizes[2]));    // 초기 상태

        // 탐색 시작
        while (!queue.isEmpty()) {
            Bottles now = queue.poll(); // 현재 물통에 담긴 물의 양

            // 중복 방지
            if (visited[now.a][now.b][now.c]) {
                continue;
            }

            // 결과 추가
            if (now.a == 0) {
                result.add(now.c);
            }

            visited[now.a][now.b][now.c] = true;

            // A -> B로 물을 붓는 경우
            if (now.a + now.b <= sizes[1]) {
                queue.add(new Bottles(0, now.a + now.b, now.c));
            } else {
                queue.add(new Bottles(now.a + now.b - sizes[1], sizes[1], now.c));
            }

            // A -> C로 물을 붓는 경우
            if (now.a + now.c <= sizes[2]) {
                queue.add(new Bottles(0, now.b, now.a + now.c));
            } else {
                queue.add(new Bottles(now.a + now.c - sizes[2], now.b, sizes[2]));
            }

            // B -> A로 물을 붓는 경우
            if (now.a + now.b <= sizes[0]) {
                queue.add(new Bottles(now.a + now.b, 0, now.c));
            } else {
                queue.add(new Bottles(sizes[0], now.a + now.b - sizes[0], now.c));
            }

            // B -> C로 물을 붓는 경우
            if (now.b + now.c <= sizes[2]) {
                queue.add(new Bottles(now.a, 0, now.b + now.c));
            } else {
                queue.add(new Bottles(now.a, now.b + now.c - sizes[2], sizes[2]));
            }

            // C -> A로 물을 붓는 경우
            if (now.a + now.c <= sizes[0]) {
                queue.add(new Bottles(now.a + now.c, now.b, 0));
            } else {
                queue.add(new Bottles(sizes[0], now.b, now.a + now.c - sizes[0]));
            }

            // C -> B로 물을 붓는 경우
            if (now.b + now.c <= sizes[1]) {
                queue.add(new Bottles(now.a, now.b + now.c, 0));
            } else {
                queue.add(new Bottles(now.a, sizes[1], now.b + now.c - sizes[1]));
            }
        }
    }

    private void solution() throws IOException {
        // 물통의 크기와 경우의 수에 대한 배열 초기화
        sizes = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        visited = new boolean[sizes[0]+1][sizes[1]+1][sizes[2]+1];

        bfs();  // 경우의 수 탐색

        // 결과 출력
        result.stream()
                .sorted()
                .forEach(e -> System.out.print(e + " "));
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

/* 세 개의 물통에 관한 클래스 */
class Bottles {

    int a;    // A 물통에 든 양
    int b;    // B 물통에 든 양
    int c;    // C 물통에 든 양

    public Bottles(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}