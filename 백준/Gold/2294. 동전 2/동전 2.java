import java.io.*;
import java.util.*;

class Solution {

    public int solution(int n, int k, int[] coins) {
        int answer = -1;    // 동전들로 k를 만들 때 필요한 동전의 최소 개수
        int[] dp = new int[k+1];    // DP 연산을 위한 배열 (인덱스들이 돈을 의미)

        // 초기화
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        // 오름차순 정렬
        Arrays.sort(coins);

        // 작은 동전부터 필요한 개수 누적하여 계산
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= k; j++) {
                // dp[i - coins[i]] : coin[i]을 추가하기 전의 금액에서 사용한 동전의 개수
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        // k를 해당 동전들로 구성한 최소 개수
        if (dp[k] != Integer.MAX_VALUE - 1) {
            answer = dp[k];
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] coins = new int[arr[0]];  // n : arr[0], k : arr[1]
        for (int i = 0; i < arr[0]; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(new Solution().solution(arr[0], arr[1], coins));;
    }
}