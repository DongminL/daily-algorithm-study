import java.io.*;
import java.util.*;

class Solution {

    public static int solution(int c, int[][] promotions) {
        // c명 늘이기 위해 형택이가 투자해야 하는 돈의 최솟값
        int answer = 10_000_001;

        // i명의 고객을 유치하기 위한 최소 비용 (i: 0 ~ c+100 까지)
        int[] dp = new int[c + 101];
        Arrays.fill(dp, 10_000_001);     // 최소값을 찾기 위한 초기값 설정
        dp[0] = 0;  // 고객을 0명 유치하는데 드는 비용은 0

        // 각 도시별 프로모션 정보 처리
        for (int[] p : promotions) {
            int cost = p[0];   // 홍보 비용
            int people = p[1]; // 그 비용으로 얻는 고객 수

            // 거꾸로 처리하여 같은 홍보가 여러 번 적용되지 않게 함
            for (int i = people; i < dp.length; i++) {
                dp[i] = Math.min(dp[i], dp[i - people] + cost);
            }
        }

        // 고객이 c 이상일 때, 홍보비의 최소값 찾기
        for (int i = c; i < dp.length; i++) {
            answer = Math.min(answer, dp[i]);
        }

        return answer;
    }
}

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] arr = toArray();
        int c = arr[0];  // 최소 고객 수
        int n = arr[1];  // 도시의 개수

        int[][] promotions = new int[n][2];
        for (int i = 0; i < n; i++) {
            promotions[i] = toArray();
        }

        System.out.println(Solution.solution(c, promotions));
    }

    private static int[] toArray() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}