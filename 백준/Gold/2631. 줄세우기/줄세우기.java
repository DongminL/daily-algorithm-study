import java.io.*;

class Solution {

    int solution(int n, int[] nums) {
        // 최장 증가 부분 수열
        int[] dp = new int[n];
        // 최장 증가 부분 수열의 길이
        int maxSize = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    maxSize = Math.max(maxSize, dp[i]);
                }
            }
        }

        // 번호 순서대로 줄을 세우는데 옮겨지는 아이들의 최소 수
        return n - maxSize;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(new Solution().solution(n, nums));
    }
}