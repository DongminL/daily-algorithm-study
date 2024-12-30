import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0; // B팀의 최대 승점
        
        // 내림차순 정렬
        Arrays.sort(A);
        Arrays.sort(B);
        
        // 두 숫자의 차가 최소일 때 B팀은 최대 승점을 가짐
        for (int i = 0, j = 0; i < A.length; i++) {
            if (A[j] < B[i]) {
                answer++;
                j++;    // A의 인덱스 증가
            }
        }
        
        return answer;
    }
}