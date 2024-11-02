import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0; // 두 수의 곱이 누적된 최솟값
        
        // 오름차순 정렬
        Arrays.sort(A); 
        Arrays.sort(B);
        
        // A의 가장 작은 수와 B의 가장 큰 수를 곱해야 누적된 값이 작아짐
        for (int i = 0; i < A.length; i++) {
            answer += A[i] * B[B.length -  (i + 1)];
        }

        return answer;
    }
}