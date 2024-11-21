import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        // 최고 집합의 원소의 수가 모자라는 경우
        if (n > s) {
            return new int[] {-1};
        }
        
        // 최고의 집합
        int[] answer = new int[n];
        
        // 몫 X n이 최고 집합을 만듦 
        int shareValue = s / n;
        
        for (int i = 0; i < n; i++) {
            answer[i] = shareValue;
        }
        
        // 나머지는 고르게 분배해준다 (가장 뒤부터 앞쪽으로)
        for (int i = 0; i < s%n; i++) {
            answer[n - i - 1]++;
        }
        
        return answer;
    }
}