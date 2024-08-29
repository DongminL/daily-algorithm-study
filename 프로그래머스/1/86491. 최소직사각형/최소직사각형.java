import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int longer = 0; // 가로와 세로 중 긴 부분
        int shorter = 0;    // 가로와 세로 중 짧은 부분
        
        // 긴쪽 중에서 최대 값과 짧은 쪽 중에서 최대값을 구함
        for (int i = 0; i < sizes.length; i++) {
            longer = Math.max(longer, Math.max(sizes[i][0], sizes[i][1]));
            shorter = Math.max(shorter, Math.min(sizes[i][0], sizes[i][1]));
        }
        
        // 긴쪽 중에서 최대 값과 짧은 쪽 중에서 최대값을 곱함
        answer = longer * shorter;
        
        return answer;
    }
}