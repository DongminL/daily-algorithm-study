import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        //  numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열
        Set<Integer> answer = new HashSet<>();
        
        // 서로 다른 인덱스에 있는 두 개의 수를 더하여 배열 구성
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                answer.add(numbers[i] + numbers[j]);
            }
        }
        
        // 오름차순 정렬 후, HashSet -> int[]
        return answer.stream()
            .sorted()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}