import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        String[] words = s.split(" ", -1);
        
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                answer.append(i % 2 == 0 ?
                    Character.toUpperCase(word.charAt(i)) :
                    Character.toLowerCase(word.charAt(i))
                );
            }
            
            answer.append(" ");
        }
        
        answer.delete(answer.length() - 1, answer.length());
        
        return answer.toString();
    }
}