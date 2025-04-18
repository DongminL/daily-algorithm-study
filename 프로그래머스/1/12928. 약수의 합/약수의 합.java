class Solution {
    public int solution(int n) {
        int answer = 0; // 약수의 총합
        
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                answer += i;
                
                if (Math.sqrt(n) != (n / i)) {
                    answer += (n / i);
                }
            }
        }
        
        return answer;
    }
}