import java.io.*;
import java.util.*;

class Solution {

    public int solution(int c, int n, int[][] ads) {
        // 형택이가 투자해야 하는 돈의 최솟값
        int answer = 2_000_000;

        // i 명의 고객을 얻는 데 드는 비용
        int[] dp = new int[c + 101];
        Arrays.fill(dp, answer);
        dp[0] = 0;  // 0명 유치 비용

        for (int[] ad : ads) {
            int cost = ad[0];   // 홍보비
            int peopleCount = ad[1];  // 홍보로 늘어나는 사람 수

            // peopleCount ~ peopleCount + 100까지 최소값을 갱신하며 
            for (int i = peopleCount; i < dp.length; i++) {
                dp[i] = Math.min(dp[i], dp[i - peopleCount] + cost);
            }
        }

        for (int i = c; i < dp.length; i++) {
            answer = Math.min(answer, dp[i]);
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 0: 호텔이 늘릴려는 최소 고객의 수 C, 1: 도시의 개수 N
        int[] inputInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();

        // {i 번째 도시에서 발생하는 홍보비, 홍보비로 얻을 수 있는 고객의 수}
        int[][] ads = new int[inputInfo[1]][2];
        for (int i = 0; i < inputInfo[1]; i++) {
            ads[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(new Solution().solution(inputInfo[0], inputInfo[1], ads));
    }
}