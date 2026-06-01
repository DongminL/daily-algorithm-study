class Solution {
    public int solution(int n) {
        long answer = 1;
        long f1 = 1;
        long f2 = 0;
        
        int DIV = 1234567;
        
        for (int i = 3; i <= n; i++) {
            f2 = f1;
            f1 = answer;
            
            answer = (f1 + f2) % DIV;
        }
        
        return (int) answer % DIV;
    }
}