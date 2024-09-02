import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        // 우선순위 큐에 삽입
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());  // 내림차순
        Arrays.stream(priorities).forEach(pq::offer);
        
        // location에 위치한 프로세스의 실행 알아내기
        int answer = 0; // location에 위치한 프로세스의 실행 순서
        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (priorities[i] == pq.peek()) {
                    pq.poll();
                    answer++;
                    
                    if (i == location) {
                        return answer;
                    }
                }
            }
        }
        
        return answer;
    }
}