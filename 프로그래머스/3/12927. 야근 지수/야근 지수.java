import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;    // 야근 피로도의 최소값
        
        // 내림차순으로 정렬
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Arrays.stream(works).forEach(maxHeap::offer);
        
        // 남은 작업들의 평균을 작게 만들기
        while (n > 0) {
            maxHeap.offer(maxHeap.poll() - 1);  // 1시간 작업하고 다시 추가
            
            n--;
        }
        
        // 야근 피로도 구하기
        for (int work : maxHeap) {
            if (work > 0) {
                answer += Math.pow(work, 2);
            }
        }
        
        return answer;
    }
}