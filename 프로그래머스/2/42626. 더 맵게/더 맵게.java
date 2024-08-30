import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        if (K == 0) {
            return K;
        }
        
        int answer = 0; // 섞어야 하는 최소 횟수
        PriorityQueue<Long> pq = new PriorityQueue<>();
        
        // 우선 순위 큐에 삽입
        Arrays.stream(scoville).mapToLong(Long::valueOf).forEach(pq::offer);
        
        // 두 스코빌 지수 섞기
        while (pq.size() > 1) {
            if (pq.peek() >= K) {
                return answer;
            }
            
            long first = pq.poll();  // 가장 맵지 않은 음식의 스코빌 지수
            long second = pq.poll(); // 두 번째로 맵지 않은 음식의 스코빌 지수
            long mix = second * 2 + first;   // 섞은 음식의 스코빌 지수
            
            pq.offer(mix);
            answer++;
        }
        
        // K 이상으로 만들 수 없는 경우
        if (pq.peek() < K) {
            return -1;
        }
        
        return answer;
    }
}