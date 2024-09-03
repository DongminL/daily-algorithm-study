import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        int[] result = {Integer.MAX_VALUE, Integer.MIN_VALUE};  // {min, max}
        
        Arrays.stream(s.split(" ")).map(Integer::parseInt).forEach(num -> {
            // 최대값 구하기
            if (num > result[1]) {
                result[1] = num;
            }
            
            // 최소값 구하기
            if (num < result[0]) {
                result[0] = num;
            }
        });
        
        // 결과 출력
        answer.append(result[0]).append(" ").append(result[1]);
        
        return answer.toString();
    }
}