class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        
        for (String word : s.split(" ", -1)) {
            if (word.equals("")) {
                answer.append(" ");
                continue;
            }
            
            answer.append(Character.toUpperCase(word.charAt(0)));
            answer.append(word.substring(1, word.length()).toLowerCase());
            answer.append(" ");
        }
        answer.delete(answer.length() - 1, answer.length());
        
        return answer.toString();
    }
}