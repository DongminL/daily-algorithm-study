import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];  // 가장 먼저 탈락하는 사람의 번호, 그 사람이 탈락한 차례
        Set<String> came = new HashSet<>(); // 말한 단어들
        
        for (int i = 0; i < words.length; i++) {
            String cur = words[i];  // 현재 말한 단어
            
            // 한 글자인 단어를 말한 경우
            if (cur.length() < 2) {
                return out(n, i);
            }
            
            // 끝말이 안 이어지는 경우
            if (i > 0 && cur.charAt(0) != words[i-1].charAt(words[i-1].length() - 1)) {
                return out(n, i);
            }
            
            // 이미 말한 단어를 말한 경우
            if (came.contains(cur)) {
                return out(n, i);
            }
            
            came.add(cur);
        }

        return new int[] {0, 0};
    }
    
    /* 탈락자 번호와 탈락한 순서 반환 */
    private int[] out(int n, int i) {
        return new int[] {(i % n) + 1, ((int) (i / (double) n) + 1)};
    }
}