import java.io.*;
import java.util.*;

class Solution {

    public int solution(int n, int k) {
        int answer = bfs(n, k); // 수빈이가 동생을 찾는 최소 시간
        return answer;
    }

    private int bfs(int start, int end) {
        // 각 노드(0 <= node < max)까지 최소 시간
        int max = 100_101;
        int[] times = new int[max];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 수빈이가 동생을 찾음
            if (current == end) {
                break;
            }

            // 뒤로 걷기
            if (current > 0 && times[current - 1] == 0) {
                queue.offer(current - 1);
                times[current - 1] = times[current] + 1;
            }

            // 잎으로 걷기
            if (current + 1 < max && times[current + 1] == 0) {
                queue.offer(current + 1);
                times[current + 1] = times[current] + 1;
            }

            // 순간이동
            if (2 * current < max && times[2 * current] == 0) {
                queue.offer(2 * current);
                times[2 * current] = times[current] + 1;
            }
        }

        return times[end];
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 0: 수빈이의 위치 N, 1: 동생의 위치 K
        int[] info = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();

        System.out.println(new Solution().solution(info[0], info[1]));
    }
}