import java.io.*;
import java.util.*;

class Solution {

    int solution(int n, int[][] table) {
        // n 번째 큰 수
        int answer = 0;

        // 내림차순
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

        // 모든 수 내림차순 정렬
        for (int[] row : table) {
            for (int number : row) {
                heap.offer(number);
            }
        }

        // n 번째로 큰 수 찾기
        for (int i = 0; i < n; i++) {
            answer = heap.poll();
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            table[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        System.out.println(new Solution().solution(n, table));
    }
}