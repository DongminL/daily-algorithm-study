import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        
        // 각 기능이 개발되는데 걸리는 시간을 큐에 삽입
        for (int i = 0; i < progresses.length; i++) {
            int time = (int) Math.ceil(((100 - progresses[i]) / (double) speeds[i]));    // 걸리는 시간
            queue.offerLast(time);
        }
        
        // 완성된 작업 확인
        while (!queue.isEmpty()) {
            int curTime = queue.pollFirst(); // 현재 기능이 걸리는 시간
            int count = 1;  // 배포될 기능의 개수
            
            // 현재 완성된 기능보다 더 빨리 마친 경우
            while (!queue.isEmpty() && curTime >= queue.peek()) {
                queue.pollFirst();
                count++;
            }
            
            answer.add(count);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}