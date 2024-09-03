import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0; // 트럭에 싣는 상자의 개수
        
        Deque<Integer> queue = new ArrayDeque<>();  // 기존 컨테이너 벨트
        Deque<Integer> stack = new ArrayDeque<>();  // 보조 컨테이너 벨트
        
        // 기존 컨테이너 벨트 채우기
        for (int i = 1; i <= order.length; i++) {
            queue.offerLast(i);
        }
        
        // 트럭에 상자 싣기
        for (int o : order) {
            while (!queue.isEmpty() && o > queue.peek()) {
                stack.offerFirst(queue.pollFirst());
            }
            
            // 두 컨테이너 벨트 확인
            if (!queue.isEmpty() && o == queue.peek()) {
                queue.pollFirst();
            } else if (!stack.isEmpty() && o == stack.peek()) {
                stack.pollFirst();
            } else {
                break;
            }
            
            // 해당 순서의 상자 싣기
            answer++;
        }
        
        
        return answer;
    }
}