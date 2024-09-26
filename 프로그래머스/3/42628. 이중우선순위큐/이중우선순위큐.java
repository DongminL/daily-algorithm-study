import java.util.*;

class Solution {
    
    PriorityQueue<Integer> minHeap;   // 오름차순
    PriorityQueue<Integer> maxHeap;   // 내림차순
    
    public int[] solution(String[] operations) {
        // answer[0] : 최대값, answer[1]: 최솟값 (없으면 둘 다 0)
        int[] answer = new int[2];  
        
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // 명령어 수행
        for (String o : operations) {
            operate(o);
        }
        
        // 숫자가 남아 있을 때
        if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
            answer[0] = maxHeap.poll();
            answer[1] = minHeap.poll();
        }
        
        return answer;
    }
    
    private void operate(String o) {
        String[] command = o.split(" ");    // "명령어 데이터"
        int data = Integer.parseInt(command[1]); // 데이터
        
        switch (command[0]) {
            case "I" :
                minHeap.offer(data);
                maxHeap.offer(data);
                break;
            
            case "D" :
                Integer deleteValue = 0;  // 삭제하는 값
                
                if (data == 1 && !maxHeap.isEmpty()) {
                    deleteValue = maxHeap.poll();
                    minHeap.remove(deleteValue);
                    
                } else if (data == -1 && !minHeap.isEmpty()) {
                    deleteValue = minHeap.poll();
                    maxHeap.remove(deleteValue);
                }
                
                break;
        }
    }
}