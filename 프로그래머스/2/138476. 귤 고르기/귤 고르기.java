import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        // 각 크기별 개수 구하기
        Map<Integer, Integer> count = new HashMap<>();
        Arrays.stream(tangerine).forEach(size -> count.put(size, count.getOrDefault(size, 0) + 1));
        
        // 개수를 오름차순 정렬
        List<Integer> list = new ArrayList<>();
        count.keySet().forEach(size -> list.add(count.get(size)));
        Collections.sort(list);
        
        // k개를 담을 때 종류의 최소 개수 구하기
        int right = list.size() - 1;
        while (right >= 0 && k > 0) {
            k -= list.get(right--);
            answer++;
        }
        
        return answer;
    }
}