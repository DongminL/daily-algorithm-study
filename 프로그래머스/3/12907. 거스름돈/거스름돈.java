import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int answer = 0; // n원을 거슬러줄 방법의 수
        int DIVIDING_VALUE = 1_000_000_007; // 나누는 값
        
        // i원을 만드는 경우의 수
        int[] dp = new int[Arrays.stream(money).sum() + 1];
        dp[0] = 1;  // 0원을 만드는 경우의 수는 무조건 하나
        
        // 누적하여 경우의 수 구하기
        for (int won : money) {
            for (int current = won; current <= n; current++) {
                // i원을 만드는 경우의 수 : 
                // (현재 만들려는 돈의 경우의 수) + (만들려는 돈 - 해당 돈)의 경우의 수
                dp[current] += dp[current - won];   
            }
        }
        
        answer = dp[n] % DIVIDING_VALUE;
        return answer;
    }
}