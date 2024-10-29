class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int pCount = 0; // p의 개수

        // 문자열에서 p,y의 개수 파악
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'p' || s.charAt(i) == 'P') {
                pCount++;
            } else if (s.charAt(i) == 'y' || s.charAt(i) == 'Y') {
                pCount--;
            }
        }
        
        // p, y의 개수가 다를 때
        if (pCount != 0) {
            answer = false;
        }

        return answer;
    }
}