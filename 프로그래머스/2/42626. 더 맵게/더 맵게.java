import java.util.*;

class Solution {
    public int solution(int[] scoville, int k) {
        if (k == 0) {
            return k;
        }
        
        // 섞어야 하는 최소 횟수
        int answer = 0; 
        
        // 우선 순위 큐에 삽입
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int v : scoville) {
            pq.offer(Long.valueOf(v));
        }
        
        // 두 스코빌 지수 섞기
        while (pq.size() > 1) {
            // 최소 스코빌이 K 이상이 될 때 종료
            if (pq.peek() >= k) {
                return answer;
            }
            
            Long first = pq.poll();  // 가장 안 매운 음식의 스코빌 지수
            Long second = pq.poll(); // 두 번째로 안 매운 음식의 스코빌 지수
            Long mix = second * 2 + first;   // 섞은 음식의 스코빌 지수
            
            pq.offer(mix);
            answer++;
        }
        
        // K 이상으로 만들 수 없는 경우
        if (pq.peek() < k) {
            return -1;
        }
        
        return answer;
    }
}