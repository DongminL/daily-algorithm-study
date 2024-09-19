class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // 연속한 값을 더하여 n 만들기
        for (int i = n; i >= (int) Math.sqrt(n); i--) {
            int sum = 0;    // 연속된 값의 합
            
            for (int j = i; j > 0; j--) {
                sum += j;
                
                if (sum == n) {
                    answer++;
                    break;
                }
                
                if (sum > n) {
                    break;
                }
            }
        }
        
        return answer;
    }
}