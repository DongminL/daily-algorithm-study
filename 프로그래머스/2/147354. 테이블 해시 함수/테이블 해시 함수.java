import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0; // 누적으로 XOR한 해시값
        
        // 주어진 조건으로 정렬
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) {
                return o2[0] - o1[0];   // 내림차순 정렬
            }
            
            return o1[col - 1] - o2[col - 1];   // 오름차순 정렬
        });
        
        // row_begin ≤ i ≤ row_end 인 모든 S_i를 누적
        for (int i = row_begin; i <= row_end; i++) {
            // S_i 계산
            int s_i = 0;
            for (int value : data[i-1]) {
                s_i += (value % i);
            }
            
            // 누적으로 XOR 연산
            answer = (answer ^ s_i);
        }
        
        return answer;
    }
}