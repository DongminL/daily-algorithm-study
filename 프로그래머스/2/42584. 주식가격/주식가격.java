import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];  // 각 시점에서 가격이 떨어지지 않은 기간
        Deque<Integer> stack = new ArrayDeque<>();    // 주가가 떨어지지 않은 시점의 인덱스만 저장
        
        // 먼저 1초 시점의 인덱스를 스택에 저장 (n초는 n-1번째 인덱스)
        stack.offerFirst(0);
        
        // 가격이 떨어지지 않은 기간 구하기 (마지막 시점은 무조건 0초이기 때문에 탐색 X)
        for (int i = 1; i < prices.length - 1; i++) {
            // 떨어진 주가의 떨어지지 않은 기간 갱신
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                Integer preStock = stack.pollFirst(); // 이전 주가의 시점
                answer[preStock] = i - preStock;
            }
            
            stack.offerFirst(i);
        }
        
        // 마지막까지 떨어지지 않은 주가의 기간 갱신
        while (!stack.isEmpty()) {
            Integer preStock = stack.pollFirst(); // 이전 주가의 시점
            answer[preStock] = (prices.length - 1) - preStock;
        }
        
        return answer;
    }
}