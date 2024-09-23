import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        // 시간의 총 범위 (분)
        long min = 1L;   // 모든 사람이 심사를 받는데 걸리는 시간의 최솟값
        long max = 1000000000L * n; // 최대값
        
        // 최소 소요 시간 탐색 (Binary Search)
        while (min <= max) {
            long mid = (min + max) / 2L;
            
            // 범위 변경
            if (isValidTime(mid, n, times)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        
        return min;
    }
    
    /* 모든 사람을 심사할 수 있는 시간인지 확인 */
    private boolean isValidTime(long time, int n, int[] times) {
        long count = 0;  // 각 심사관이 time 내에 처리할 수 있는 사람의 수
        
        for (int t : times) {
            count += time / t;    
        }
        
        return count >= n;
    }
}