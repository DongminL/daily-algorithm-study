class Solution {
    public String solution(String s) {
        String answer = "";
        int midIndex = s.length() / 2;
        
        if (s.length() % 2 == 0) {
            answer = s.substring(midIndex - 1, midIndex + 1);
        } else {
            answer = s.substring(midIndex, midIndex + 1);
        }
        
        return answer;
    }
}