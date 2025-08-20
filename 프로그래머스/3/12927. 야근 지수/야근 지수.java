import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 야근 피로도의 최소값
        long answer = 0;    
        
        // 내림차순으로 정렬
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            maxHeap.offer(work);
        }
        
        // 많이 남은 작업을 우선으로 처리하기 (평균 줄이는 목적)
        for (int i = 0; i < n; i++) {
            maxHeap.offer(maxHeap.poll() - 1);
        }
        
        // 야근 피로도 구하기
        answer = maxHeap.stream()
            .filter(remain -> remain > 0)
            .mapToLong(remain -> remain * remain)
            .sum();
        
        return answer;
    }
}