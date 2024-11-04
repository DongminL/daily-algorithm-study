class Solution {
    public long solution(int n) {
        long answer = 0;    // 효진이가 끝에 도달하는 방법의 가지 수
        final int DIVISION = 1234567;  // 나누는 값
        
        // 멀리 뛰기 경우의 수 구하기 (피보나치 수열과 비슷)
        if (n < 3) {
            return n;
        }
        
        long f0 = 1; // f(n-2)
        long f1 = 1; // f(n-1);
        for (int i = 2; i <= n; i++) {
            answer = (f0 + f1) % DIVISION;
            f0 = f1;
            f1 = answer;
        }
        
        
        return answer;
    }
}