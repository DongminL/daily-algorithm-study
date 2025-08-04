import java.io.*;
import java.util.*;

class Solution {

    public int[] solution(int n, int[] nums) {
        // 각 수를 1,2,3을 더하여 만들 수 있는 경우의 수
        int[] answer = new int[n];

        int maxNum = Arrays.stream(nums).max().getAsInt();
        int[] dp = new int[maxNum + 1];

        // 1로만 더하는 경우 (dp[0] = 1은 0+2와 0+3에 대한 경우)
        Arrays.fill(dp, 1);

        // 1, 2만으로 더하는 경우
        for (int i = 2; i < dp.length; i++) {
            dp[i] += dp[i - 2];
        }

        // 1, 2, 3으로 더하는 경우
        for (int i = 3; i < dp.length; i++) {
            dp[i] += dp[i - 3];
        }

        // 각 값에 대한 경우의 수 정리
        for (int i = 0; i < n; i++) {
            answer[i] = dp[nums[i]];
        }

        return answer;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());    // 테스트 케이스 개수

        // 각 테스트 케이스에 대한 양의 정수들
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        // 결과 출력
        int[] answer = new Solution().solution(n, nums);
        Arrays.stream(answer).forEach(System.out::println);
    }
}