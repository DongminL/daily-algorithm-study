import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0; // 캐시 크기에 따른 실행시간
        
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        // 인덱스의 크기가 작을수록 교체 우선순위가 높음
        Deque<String> cacheQueue = new ArrayDeque<>(cacheSize);
        
        for (String city : cities) {
            String lowerName = city.toLowerCase();
            
            // cache hit
            if (cacheQueue.contains(lowerName)) {
                answer += 1;
                
                // 맨 뒤로 순서 변경
                cacheQueue.remove(lowerName);
                cacheQueue.offerLast(lowerName);
                continue;
            }
            
            // cache miss
            if (cacheQueue.size() < cacheSize) {
                cacheQueue.offerLast(lowerName);
            } else {    // 캐시 공간이 가득 찼을 때
                cacheQueue.pollFirst();
                cacheQueue.offerLast(lowerName);
            }
            answer += 5;
        }
        
        return answer;
    }
}