import java.util.*;

class Solution {
    public int[] solution(long n) {
        // 자연수 n을 뒤집어 각 자리 숫자를 원소로 가지는 배열
        List<Integer> answer = new ArrayList<>();   
        
        // 자연수 뒤집기
        while (n > 0) {
            int reverseNum = (int) (n % 10L);    // n을 뒤집어 읽을 때의 각 자리의 수
            answer.add(reverseNum);
            
            n /= 10;    // 매번 일의 자리수를 없애기
        }
        
        // List<Integer> -> int[]
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}