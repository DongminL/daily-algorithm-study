import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        int n = citations.length;  // 논문의 개수
        
        // 오름차순 정렬
        Arrays.sort(citations);
        
        // H-Index 판별
        for (int h = citations[n-1]; h >= 0; h--) {
            // h번 이상 인용된 논문의 개수
            int count = 0;
            for (int i = n-1; i >= 0; i--) {
                if (h > citations[i]) {
                    break;
                }
                
                count++;
            }
            
            // H-Index일 때
            if (h <= count) {
                return h;
            }
        }
        
        return answer;
    }
}