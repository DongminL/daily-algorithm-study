class Solution {
    public String solution(String[] seoul) {
        String answer = "김서방은 %s에 있다";
        
        int index = 0;
        for (String name : seoul) {
            if (name.equals("Kim")) {
                break;
            }
            index++;
        }
        
        return String.format(answer, index);
    }
}