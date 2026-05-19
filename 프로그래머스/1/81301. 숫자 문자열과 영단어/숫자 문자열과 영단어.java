class Solution {
    public int solution(String s) {
        String[] numberWords = { 
            "zero", "one", "two", 
            "three", "four", "five", 
            "six", "seven", "eight", "nine" 
        };
        
        // word -> number
        for (int i = 0; i < 10; i++) {
            s = s.replaceAll(numberWords[i], String.valueOf(i));
        }
        
        return Integer.parseInt(s);
    }
}