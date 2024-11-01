import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0; // 모든 트럭이 다리를 건너는 데 걸리는 시간
        int currentTruck = 0;   // 현재 트럭의 인덱스 번호
        int currentWeight = 0;  // 다리에 올라가 있는 무게
        int finishCount = 0;    // 다리에서 내려온 트럭의 수
        
        Deque<Truck> queue = new ArrayDeque<>();   // 다리에 올라간 트럭들
        
        // 다리 건너기
        while (finishCount < truck_weights.length) {
            time++;   // 1초 추가
            
            // 트럭이 다리에서 내려갈 경우
            if (!queue.isEmpty() && queue.peek().startTime + bridge_length <= time) {
                currentWeight -= queue.pollFirst().weight;
                finishCount++;
            }
            
            // 모든 트럭이 다리를 건넜을 때
            if (finishCount == truck_weights.length) {
                break;
            }
            
            // 트럭이 다리에 올라갈 경우
            if ((currentTruck < truck_weights.length) && 
                (queue.size() < bridge_length) && (currentWeight + truck_weights[currentTruck] <= weight)) {
                
                queue.offerLast(new Truck(truck_weights[currentTruck], time));
                
                currentWeight += truck_weights[currentTruck++];
            }
        }
        
        return time;
    }
}

/* 트럭에 관한 클래스 */
class Truck {
    
    int weight; // 트럭의 무게
    int startTime;  // 다리에 올라간 시간
    
    public Truck(int weight, int startTime) {
        this.weight = weight;
        this.startTime = startTime;
    }
}