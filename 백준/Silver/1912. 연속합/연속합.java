import java.io.*;
import java.util.*;

class Solution {

    public int solution(int n, int[] sequence) {
        int answer = sequence[0]; // 연속합의 최대값
        int[] dp = new int[n];  // 각 자리까지의 연속합의 최대값

        // i 번째까지의 연속합의 최대값 구하기
        dp[0] = sequence[0];
        for (int i = 1; i < n; i++) {
            // i 번째까지의 연속합의 최대값
            dp[i] = Math.max(dp[i-1] + sequence[i], sequence[i]);

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    // 수의 개수
        int[] sequence = Arrays.stream(br.readLine().split(" "))    // 수열
                .mapToInt(Integer::parseInt).toArray();


        System.out.println(new Solution().solution(n, sequence));
    }
}