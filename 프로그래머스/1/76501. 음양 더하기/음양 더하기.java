class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        
        for (int i = 0; i < absolutes.length; i++) {
            int num = signs[i] ? absolutes[i] : -1 * absolutes[i];
            answer += num;
        }
        
        return answer;
    }
}