class Solution {
    public int solution(int n) {
        long answer = 1; // F(n)
        long n1 = 1; // F(n-1)
        long n2 = 0; // F(n-2)
        final int DIV = 1234567;
        
        // n일 때 피보나치 수 구하기
        for (int i = 3; i <= n; i++) {
            n2 = n1;
            n1 = answer;
            
            answer = (n1 + n2) % DIV;
        }
        
        return (int) answer % DIV;
    }
}