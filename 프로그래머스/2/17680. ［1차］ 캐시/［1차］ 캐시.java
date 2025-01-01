import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0; // 캐시 크기에 따른 실행시간
        
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        // 인덱스의 크기가 작을수록 교체 우선순위가 높음
        List<String> cache = new ArrayList<>(cacheSize);
        
        for (String city : cities) {
            String lowerName = city.toLowerCase();
            
            // cache hit
            if (cache.contains(lowerName)) {
                answer += 1;
                
                // 맨 뒤로 순서 변경
                cache.remove(lowerName);
                cache.add(lowerName);
                continue;
            }
            
            // cache miss
            if (cache.size() < cacheSize) {
                cache.add(lowerName);
            } else {    // 캐시 공간이 가득 찼을 때
                cache.remove(0);
                cache.add(lowerName);
            }
            answer += 5;
        }
        
        return answer;
    }
}