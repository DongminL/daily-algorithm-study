class Solution {
    public long solution(long n) {
        long answer = 0;
        double sqrt = Math.sqrt(n);
        
        answer = sqrt % 1 == 0.0 ? ((long) Math.pow(sqrt + 1, 2)) : -1L;
        
        return answer;
    }
}