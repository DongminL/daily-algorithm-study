import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        // location에 위치한 프로세스의 실행 순서
        int answer = 0; 
        
        // 내림차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());  
        for (int priority : priorities) {
            pq.offer(priority);
        }
        
        // location에 위치한 프로세스의 실행 순서 구하기
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