import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        boolean answer = true;
        
        Queue<String> cardsA = new ArrayDeque<>(Arrays.asList(cards1));
        Queue<String> cardsB = new ArrayDeque<>(Arrays.asList(cards2));
        
        for (String word : goal) {
            if (!cardsA.isEmpty() && word.equals(cardsA.peek())) {
                cardsA.poll();
            } else if (!cardsB.isEmpty() && word.equals(cardsB.peek())) {
                cardsB.poll();
            } else {
                answer = false;
                break;
            }
        }
        
        return answer ? "Yes" : "No";
    }
}