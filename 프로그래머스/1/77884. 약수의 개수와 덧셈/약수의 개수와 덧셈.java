class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        
        for (int num = left; num <= right; num++) {
            if (num % Math.sqrt(num) == 0) {
                answer -= num;
            } else {
                answer += num;
            }
        }
        
        return answer;
    }
}