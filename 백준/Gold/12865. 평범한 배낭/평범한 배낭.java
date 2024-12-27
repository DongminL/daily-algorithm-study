import java.util.*;
import java.io.*;

class Solution {

    public static int solution(int n, int k, int[][] items) {
        // row: 물품의 순서(0 ~ N), col: 무게의 경우(0 ~ K), value: 해당 무게의 최대 가치
        int[][] dp = new int[n + 1][k + 1];

        for (int idx = 1; idx <= n; idx++) {
            for (int weight = 1; weight <= k; weight++) {
                int itemWeight = items[idx][0];

                if (itemWeight <= weight) { // k를 넘지 않을 정도의 무게를 가질 때, 최대값 갱신
                    dp[idx][weight] = Math.max(dp[idx - 1][weight],
                        dp[idx - 1][weight - itemWeight] + items[idx][1]);
                } else {    // 현재 물품보다 적은 무게는 고려하지 않아도 됨
                    dp[idx][weight] = dp[idx - 1][weight];
                }
            }
        }

        return dp[n][k];    // 배낭에 넣을 수 있는 물건들의 가치의 최댓값
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] initVals = br.readLine().split(" ");
        int N = Integer.parseInt(initVals[0]);    // 물품 수
        int K = Integer.parseInt(initVals[1]);    // 배낭의 최대 무게

        int[][] items = new int[N + 1][2];    // {{weight, value}, ...}
        for (int i = 1; i <= N; i++) {
            String[] item = br.readLine().split(" ");
            items[i][0] = Integer.parseInt(item[0]);    // 물품의 무게
            items[i][1] = Integer.parseInt(item[1]);    // 물품의 가치
        }

        System.out.println(Solution.solution(N, K, items));
    }
}