import java.util.*;

class Solution {
    public String solution(String number, int k) {
        // number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자
        StringBuilder answer = new StringBuilder();
        
        int start = 0;  // 시작 인덱스
        
        // k개의 수를 제거할 때 가장 큰 숫자 구하기
        while (start < number.length() && answer.length() < number.length() - k) {
            int len = answer.length() + k + 1;  // 탐색할 구간
            int max = 0;    // 해당 구간에서 가장 큰 수
            
            // 가장 큰 숫자 찾기
            for (int i = start; i < len; i++) {
                if (max < number.charAt(i) - '0') {
                    max = number.charAt(i) - '0';
                    start = i + 1;  // 다음 구간으로 넘어가기
                }
            }
            
            answer.append(max);
        }
        
        return answer.toString();
    }
}