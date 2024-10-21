import java.io.*;
import java.util.*;

class Solution {

    public int solution(int n, int[] T, int[] P) {
        int max = 0; // 얻을 수 있는 최대 수익
        int[] incomes = new int[n+2];    // 1 ~ N일부터 시작하여 퇴사할 때까지 벌 수 있는 최대 수익 (DP)

        // 각 날짜까지 벌 수 있는 최대값 구하기
        for (int i = 1; i < n+2; i++) {
            max = Math.max(max, incomes[i]); // 최대값 갱신

            int nextDate = i + T[i];    // 다음 상담을 시작할 수 있는 날짜

            // 퇴사(N+1일) 전까지 상담할 때 수익 얻음
            if (nextDate < n + 2) {
                incomes[nextDate] = Math.max(incomes[nextDate], max + P[i]);
            }
        }

        // 마지막 날까지 번 최대수입
        return max;
    }
}

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());    // 상담할 일 수
        int[] T = new int[N+2]; // 상담을 완료하는 데 걸리는 기간
        int[] P = new int[N+2]; // 상담을 했을 때 받을 수 있는 금액

        for (int i = 1; i <= N; i++) {
            // 입력값
            int[] input = toIntArray();

            T[i] = input[0];
            P[i] = input[1];
        }

        System.out.println(new Solution().solution(N, T, P));
    }

    /* 입력값 (문자열) -> int[] */
    private static int[] toIntArray() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}